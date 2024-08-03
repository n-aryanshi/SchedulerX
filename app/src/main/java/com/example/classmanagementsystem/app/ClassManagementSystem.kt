package com.example.classmanagementsystem.app

import androidx.compose.animation.Crossfade
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.classmanagementsystem.data.database.DataStoreManager
import com.example.classmanagementsystem.data.database.RoomViewModel
import com.example.classmanagementsystem.data.database.TeacherViewModel
import com.example.classmanagementsystem.data.home.HomeViewModel
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.screens.AboutScreen
import com.example.classmanagementsystem.screens.ClassroomScreen
import com.example.classmanagementsystem.screens.FacultiesScreen
import com.example.classmanagementsystem.screens.ForgotPasswordScreen
import com.example.classmanagementsystem.screens.Home
import com.example.classmanagementsystem.screens.HomeScreen
import com.example.classmanagementsystem.screens.HomeTimetable
import com.example.classmanagementsystem.screens.LoginScreen
import com.example.classmanagementsystem.screens.Meeting
import com.example.classmanagementsystem.screens.Profile
import com.example.classmanagementsystem.screens.ScheduleScreen
import com.example.classmanagementsystem.screens.SearchScreen
import com.example.classmanagementsystem.screens.SettingsScreen
import com.example.classmanagementsystem.screens.SignUpScreen
import com.example.classmanagementsystem.screens.TermsAndConditionScreen
import com.example.classmanagementsystem.screens.TimetableScreen
import com.example.classmanagementsystem.screens.TodayScheduleScreen
import com.example.classmanagementsystem.screens.WalkThroughScreen

@Composable
fun ClassManagementSystem(
    dataStoreManager: DataStoreManager,
    homeViewModel: HomeViewModel = viewModel(), teacherViewModel: TeacherViewModel = viewModel(), roomViewModel: RoomViewModel = viewModel()
) {
    teacherViewModel.addTeacherManually()
    roomViewModel.addRoomManually()
    homeViewModel.checkForActiveSession()

    Surface(
//        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            AppRouter.navigateTo(Screen.HomeScreen)
        }

        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.TermsAndConditionScreen -> {
                    TermsAndConditionScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }
                is Screen.Profile->{
                    Profile(dataStoreManager)
                }
                is Screen.Settings->{
                    SettingsScreen()
                }
                is Screen.Home->{
                    Home()
                }
                is Screen.Meeting->{
                    Meeting()
                }
                is Screen.WalkThroughScreen->{
                    WalkThroughScreen()
                }
                is Screen.AboutScreen->{
                    AboutScreen()
                }
                is Screen.ForgotPasswordScreen->{
                    ForgotPasswordScreen()
                }
                is Screen.ScheduleScreen->{
                    ScheduleScreen()
                }
                is Screen.SearchScreen->{
                    SearchScreen()
                }
                is Screen.TimetableScreen->{
                    TimetableScreen()
                }
                is Screen.FacultiesScreen->{
                    FacultiesScreen()
                }
                is Screen.ClassroomScreen->{
                    ClassroomScreen()
                }
                is Screen.TodayScheduleScreen->{
                    TodayScheduleScreen()
                }
                is Screen.HomeTimeTable->{
                    HomeTimetable()
                }
            }
            
        }

    }
}