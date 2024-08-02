package com.example.classmanagementsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.classmanagementsystem.app.ClassManagementSystem
import com.example.classmanagementsystem.data.database.DataStoreManager
import com.example.classmanagementsystem.ui.theme.ClassManagementSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        // Initialize DataStoreManager
        val dataStoreManager = DataStoreManager(context = this)

        setContent {
            ClassManagementSystemTheme {
                // Pass DataStoreManager instance to ClassManagementSystem
                ClassManagementSystem(dataStoreManager = dataStoreManager)
            }
        }
    }
}


//@Preview
//@Composable
//fun DefaultPreview(){
//    val dataStoreManager = DataStoreManager(context = /* provide a mock context if needed */)
//    ClassManagementSystem(dataStoreManager = dataStoreManager)
//}