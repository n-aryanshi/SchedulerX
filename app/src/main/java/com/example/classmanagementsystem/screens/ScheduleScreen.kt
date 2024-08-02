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
fun ScheduleScreen(){
    val context = LocalContext.current
    // Meeting Time
    val classTimeState = remember { mutableStateOf("") }
    // Room Number
    val roomNoState = remember { mutableStateOf("") }
    // Meeting Description
    val subjectState = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            FileTopBar(
                toolbarTitle = stringResource(id = R.string.schedule),
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
                text = "Schedule a Class",
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            TextField(
                value = classTimeState.value,
                onValueChange = { classTimeState.value = it },
                label = { Text("Class Time") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                maxLines = 1,
            )

            TextField(
                value = roomNoState.value,
                onValueChange = { roomNoState.value = it },
                label = { Text("Room Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                maxLines = 1,
            )


            TextField(
                value = subjectState.value,
                onValueChange = { subjectState.value = it },
                label = { Text("Subject") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                singleLine = true,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                onClick = {
                    val classTime = classTimeState.value
                    val roomNo = roomNoState.value
                    val subjectDesc = subjectState.value
                    // Check if any of the fields are empty
                    if (classTime.isBlank() || roomNo.isBlank() || subjectDesc.isBlank()) {
                        // Show error message if any field is empty
                        Toast.makeText(
                            context,
                            "Please fill all fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val toastText = "Meeting scheduled for $classTime in Room $roomNo: $subjectDesc"
                        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                    }
                },

                ) {
                Text(
                    text="Schedule Class",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        SystemBackButtonHandler {
            AppRouter.navigateTo(Screen.HomeScreen)
        }
    }
}