package com.example.classmanagementsystem.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.ui.components.FileTopBar
import com.example.classmanagementsystem.data.RoomViewModel
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.navigation.SystemBackButtonHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassroomScreen(roomViewModel: RoomViewModel = viewModel()){
    var inputRoom by remember { mutableStateOf("") }
    var listOfRooms by remember { mutableStateOf<List<String>>(emptyList()) }
    var filteredRoom by remember { mutableStateOf(emptyList<String>()) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        roomViewModel.getRoom().collect { rooms ->
            listOfRooms = rooms.map { it.roomNo }
            filteredRoom = listOfRooms
        }
    }
    // Collect filtered names when inputName changes
    LaunchedEffect(inputRoom) {
        filteredRoom = listOfRooms.filter { it.contains(inputRoom, ignoreCase = true) }
    }
    Scaffold(
        topBar = {
            FileTopBar(
                toolbarTitle = stringResource(id = R.string.classroom),
                navigationIconClicked = {
                    AppRouter.navigateTo(Screen.HomeScreen)
                }
            )
        }
    ){innerPadding->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .padding(innerPadding),
//                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = inputRoom,
                onValueChange = { inputRoom = it },
                label = { Text("Enter a room number to search") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Button(
                onClick = {
                    if (inputRoom.isBlank()) {
                        // Show toast for empty input
                        Toast.makeText(context, "Please enter a room number to search", Toast.LENGTH_SHORT).show()
                    } else {
                        filteredRoom = listOfRooms.filter { it.contains(inputRoom, ignoreCase = true) }
                        if (filteredRoom.isEmpty()) {
                            // Show toast for name not found
                            Toast.makeText(context, "Room not found", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search")
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn (
                modifier = Modifier.fillMaxWidth(), // Expand LazyColumn to full width
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp) // Add padding
            ){
                itemsIndexed(filteredRoom) { index, name ->
                    RoomItem(name)
                }
            }

        }
        SystemBackButtonHandler {
            AppRouter.navigateTo(Screen.HomeScreen)
        }
    }
}


@Composable
fun RoomItem(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.room),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}