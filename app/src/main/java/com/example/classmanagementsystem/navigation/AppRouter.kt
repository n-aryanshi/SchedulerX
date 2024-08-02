package com.example.classmanagementsystem.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen{
    object SignUpScreen:Screen()
    object TermsAndConditionScreen:Screen()
    object WalkThroughScreen:Screen()
    object  LoginScreen:Screen()
    object  HomeScreen:Screen()
    object Profile:Screen()
    object  Settings:Screen()
    object Home:Screen()
    object Meeting:Screen()
    object AboutScreen:Screen()
    object ForgotPasswordScreen:Screen()
    object SearchScreen:Screen()
    object ScheduleScreen:Screen()
    object TodayScheduleScreen:Screen()
    object TimetableScreen:Screen()
    object FacultiesScreen:Screen()
    object ClassroomScreen:Screen()

}
object AppRouter{
    var currentScreen: MutableState<Screen> =mutableStateOf(Screen.WalkThroughScreen)
    fun navigateTo(destination:Screen){
        currentScreen.value=destination
    }
}