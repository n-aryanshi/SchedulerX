package com.example.classmanagementsystem.screens

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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.ui.theme.Primary
import com.example.classmanagementsystem.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAndConditionScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Privacy Policy") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Primary,
                    titleContentColor = TextColor
                )
//                backgroundColor = Color.Black
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = "Privacy Policy",
                style= TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "At OurApp, we take your privacy seriously. This Privacy Policy explains how we collect, use, and disclose information about you when you use our mobile application and website. By accessing or using the Service, you agree to the terms of this Privacy Policy. \n\n We may collect information about you directly from you and from third parties, as well as automatically through your use of our Service.",

                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Information We Collect",style= TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "We may collect personal information such as your name, email address, and phone number. We also collect non-personal information such as device information, usage data, and cookies.",

                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "How We Use Your Information",style= TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "We may use your information to provide, maintain, and improve our services, to communicate with you, to personalize your experience, and for other legitimate purposes.",

                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Changes to This Privacy Policy",style= TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "We may update our Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page.",

                modifier = Modifier.padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { AppRouter.navigateTo(Screen.SignUpScreen) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Accept")
            }
        }
    }
}

@Preview
@Composable
fun TermAndConditionScreenPreview(){
    TermsAndConditionScreen()
}