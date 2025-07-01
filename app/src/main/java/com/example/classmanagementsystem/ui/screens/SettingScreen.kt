package com.example.classmanagementsystem.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.ui.components.FileTopBar
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.navigation.SystemBackButtonHandler

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SettingsScreen() {
//    var isDarkTheme by remember { mutableStateOf(false) }
//
//    Scaffold(
//        topBar = {
//            FileTopBar(
//                toolbarTitle = stringResource(id = R.string.settings),
//                navigationIconClicked = {
//                    AppRouter.navigateTo(Screen.HomeScreen)
//                }
//            )
//        }
//    ){ innerPadding ->
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .padding(innerPadding)
//                .verticalScroll(rememberScrollState())
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Text(text = "Change Theme", style = MaterialTheme.typography.headlineSmall)
//
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text(text = "Dark Theme", style = MaterialTheme.typography.bodyLarge)
//
//                Spacer(modifier = Modifier.width(16.dp))
//
//                Switch(
//                    checked = isDarkTheme,
//                    onCheckedChange = { isChecked ->
//                        // Update the theme based on the switch state
//                        isDarkTheme = isChecked
//                    }
//                )
//            }
//        }
//        SystemBackButtonHandler {
//            AppRouter.navigateTo(Screen.HomeScreen)
//        }
//    }
//
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    // State for notification preference
    val notificationEnabledState = remember { mutableStateOf(false) }

    // State for dark mode preference
    val darkModeEnabledState = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            FileTopBar(
                toolbarTitle = stringResource(id = R.string.settings),
                navigationIconClicked = {
                    AppRouter.navigateTo(Screen.HomeScreen)
                }
            )
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Notification preference
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Enable Notifications",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = notificationEnabledState.value,
                    onCheckedChange = { notificationEnabledState.value = it },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Dark mode preference
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dark Mode",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = darkModeEnabledState.value,
                    onCheckedChange = { darkModeEnabledState.value = it },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            // Button to save settings
            Button(
                onClick = {
                    Toast.makeText(context, "Saving", Toast.LENGTH_SHORT).show()
                },

            ) {
                Text(
                    text="Save Settings",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Button(
                onClick = {
                    Toast.makeText(context, "Saving", Toast.LENGTH_SHORT).show()
                },

                ) {
                Text(
                    text="Undo Settings",
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

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
