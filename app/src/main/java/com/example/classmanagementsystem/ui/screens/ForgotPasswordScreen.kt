package com.example.classmanagementsystem.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.ui.components.FileTopBar
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.navigation.SystemBackButtonHandler
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(){
    val emailState = remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    val passwordResetState = remember { mutableStateOf<PasswordResetState?>(null) }

    Scaffold(
        topBar = {
            FileTopBar(
                toolbarTitle = stringResource(id = R.string.forgot),
                navigationIconClicked = {
                    AppRouter.navigateTo(Screen.LoginScreen)
                }
            )
        }
    ){innerPadding->
        Column(
            modifier= Modifier
                .padding(16.dp)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){

            TextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                label = { Text("Email") }
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = { sendPasswordResetEmail(emailState.value, auth, passwordResetState) }) {
                Text("Reset Password")
            }

            passwordResetState.value?.let { state ->
                when (state) {
                    is PasswordResetState.Success -> {
                        Text("Password reset email sent successfully")
                    }
                    is PasswordResetState.Error -> {
                        Text("Error sending password reset email: ${state.message}")
                    }
                }
            }
        }
        SystemBackButtonHandler {
            AppRouter.navigateTo(Screen.LoginScreen)
        }
    }
}

sealed class PasswordResetState {
    object Success : PasswordResetState()
    data class Error(val message: String) : PasswordResetState()
}

private fun sendPasswordResetEmail(email: String, auth: FirebaseAuth, state: MutableState<PasswordResetState?>) {
    auth.sendPasswordResetEmail(email)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                state.value = PasswordResetState.Success
            } else {
                state.value = PasswordResetState.Error(task.exception?.message ?: "Unknown error")
            }
        }
}

@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen()
}