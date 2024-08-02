package com.example.classmanagementsystem.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.components.FileTopBar
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.navigation.SystemBackButtonHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Meeting(){
    val context = LocalContext.current

    Scaffold(
        topBar = {
            FileTopBar(
                toolbarTitle = stringResource(id = R.string.meeting),
                navigationIconClicked = {
                    AppRouter.navigateTo(Screen.HomeScreen)
                }
            )
        }
    ){innerPadding->
        Column(
            modifier= Modifier
                .padding(16.dp)
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
//                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(
                text = "Schedule a Meeting",
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp),


            )

            // Meeting Time
            val meetingTimeState = remember { mutableStateOf("") }
            TextField(
                value = meetingTimeState.value,
                onValueChange = { meetingTimeState.value = it },
                label = { Text("Meeting Time") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                maxLines = 1
            )

            // Room Number
            val roomNoState = remember { mutableStateOf("") }
            TextField(
                value = roomNoState.value,
                onValueChange = { roomNoState.value = it },
                label = { Text("Room Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                maxLines = 1
            )

            // Meeting Description
            val meetingDescState = remember { mutableStateOf("") }
            TextField(
                value = meetingDescState.value,
                onValueChange = { meetingDescState.value = it },
                label = { Text("Meeting Description") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                singleLine = true,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    val meetingTime = meetingTimeState.value
                    val roomNo = roomNoState.value
                    val meetingDesc = meetingDescState.value
                    // Check if any of the fields are empty
                    if (meetingTime.isBlank() || roomNo.isBlank() || meetingDesc.isBlank()) {
                        // Show error message if any field is empty
                        Toast.makeText(
                            context,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val toastText = "Meeting scheduled for $meetingTime in Room $roomNo: $meetingDesc"
                        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                    }
                },

            ) {
                Text(
                    text="Post Meeting",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,)
            }
        }
        SystemBackButtonHandler {
            AppRouter.navigateTo(Screen.HomeScreen)
        }
    }
}