package com.example.classmanagementsystem.screens

import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen

@Composable
fun WalkThroughScreen(){

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            val view= LayoutInflater.from(context.applicationContext).inflate(R.layout.activity_intro, null, false)
            view.apply{
                findViewById<AppCompatButton>(R.id.button2).setOnClickListener{
                    AppRouter.navigateTo(Screen.SignUpScreen)
                }
            }
            view
        }
    )

}

@Preview
@Composable
fun WalkThroughScreenPreview(){
    WalkThroughScreen()
}

