package com.example.classmanagementsystem.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.components.ButtonComponent
import com.example.classmanagementsystem.components.ClickableLoginTextComponent
import com.example.classmanagementsystem.components.DividerTextComponent
import com.example.classmanagementsystem.components.HeadingTextComponent
import com.example.classmanagementsystem.components.MyTextFieldComponent
import com.example.classmanagementsystem.components.NormalTextComponent
import com.example.classmanagementsystem.components.PasswordTextFieldComponent
import com.example.classmanagementsystem.components.UnderLinedTextComponent
import com.example.classmanagementsystem.data.login.LoginUIEvent
import com.example.classmanagementsystem.data.login.LoginViewModel
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel() ) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
                .background(color = Color.White)
                .verticalScroll(rememberScrollState()),

            ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome_back))
                Spacer(modifier = Modifier.height(20.dp))
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.mail),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )
                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.password),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )
                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(
                    value = stringResource(id = R.string.forgot_password),
                ){
                    AppRouter.navigateTo(Screen.ForgotPasswordScreen)

                }
                Spacer(modifier = Modifier.height(40.dp))
                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()
                ClickableLoginTextComponent(
                    tryingToLogin = false,
                    onTextSelected = {
                        AppRouter.navigateTo(Screen.SignUpScreen)
                    }
                )

            }
        }
        SystemBackButtonHandler {
            AppRouter.navigateTo(Screen.SignUpScreen)
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfLoginScreen(){
    LoginScreen()
}