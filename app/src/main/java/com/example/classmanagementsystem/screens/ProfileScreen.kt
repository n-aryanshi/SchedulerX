package com.example.classmanagementsystem.screens

import ProfileViewModelFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.components.FileTopBar
import com.example.classmanagementsystem.data.database.DataStoreManager
import com.example.classmanagementsystem.data.database.ProfileViewModel
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.navigation.SystemBackButtonHandler

@Composable
fun Profile(dataStoreManager: DataStoreManager) {
    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(dataStoreManager)
    )
    val user by viewModel.user.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var newName by remember { mutableStateOf(user.name) }
    var newDesignation by remember { mutableStateOf(user.designation) }

    // Image picker launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.updateUserProfilePicture(it.toString())
        }
    }

    Scaffold(
        topBar = {
            FileTopBar(
                toolbarTitle = stringResource(id = R.string.profile),
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            ProfilePicture(user = user, modifier = Modifier.size(150.dp))
            ChangeProfilePictureButton(modifier = Modifier.padding(top = 8.dp)){
                launcher.launch("image/*")
            }
            UserInfo(user = user)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { showDialog = true }) {
                Text(text = "Edit Profile")
            }
            if (showDialog) {
                EditProfileDialog(
                    newName = newName,
                    newDesignation = newDesignation,
                    onNameChange = { newName = it },
                    onDesignationChange = { newDesignation = it },
                    onSave = {
                        viewModel.updateUserProfile(newName, newDesignation)
                        showDialog = false
                    },
                    onDismiss = { showDialog = false }
                )
            }
        }
        SystemBackButtonHandler {
            AppRouter.navigateTo(Screen.HomeScreen)
        }
    }
}

@OptIn(coil.annotation.ExperimentalCoilApi::class)

@Composable
fun ProfilePicture(user: User, modifier: Modifier) {
    Image(
        painter = rememberImagePainter(data = user.profilePictureUrl),
        contentDescription = "Profile Picture",
        modifier = modifier
            .clip(shape = CircleShape)
            .border(2.dp, Color.White, CircleShape)
    )
}

@Composable
fun ChangeProfilePictureButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    TextButton(
        onClick =  onClick,
        modifier = modifier
    ) {
        Text(text = "Change Profile Picture")
    }
}

@Composable
fun UserInfo(user: User) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = user.name,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = user.email,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = user.designation,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Composable
fun EditProfileDialog(
    newName: String,
    newDesignation: String,
    onNameChange: (String) -> Unit,
    onDesignationChange: (String) -> Unit,
    onSave: () -> Unit,
    onDismiss: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Edit Profile") },
        confirmButton = {
            Button(
                onClick = {
                    onSave()
                    onDismiss()
                }
            ) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text(text = "Cancel")
            }
        },
        text = {
            Column {
                OutlinedTextField(
                    value = newName,
                    onValueChange = onNameChange,
                    label = { Text(text = "Name") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    singleLine = true,
                    maxLines = 1,
                )
                OutlinedTextField(
                    value = newDesignation,
                    onValueChange = onDesignationChange,
                    label = { Text(text = "Designation") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onSave()
                            onDismiss()
                        }
                    )
                )
            }
        }
    )
}

data class User(
    val name: String,
    val email: String,
    val designation: String,
    val profilePictureUrl: String
)
//@Preview
//@Composable
//fun ProfilePreview(){
//    Profile(DataStoreManager(context = Context))
//}