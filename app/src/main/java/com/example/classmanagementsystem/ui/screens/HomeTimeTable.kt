package com.example.classmanagementsystem.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.ui.components.FileTopBar
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.navigation.SystemBackButtonHandler

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeTimetable() {
    Scaffold(
        topBar = {
            FileTopBar(
                toolbarTitle = stringResource(id = R.string.TimeTable),
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

        }
        SystemBackButtonHandler {
            AppRouter.navigateTo(Screen.HomeScreen)
        }
    }
}