package com.example.classmanagementsystem.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.components.AppToolbar
import com.example.classmanagementsystem.components.BottomSheetItem
import com.example.classmanagementsystem.components.NavigationDrawerHeader
import com.example.classmanagementsystem.data.home.HomeViewModel
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.ui.theme.Primary
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val context= LocalContext.current.applicationContext
    val coroutineScope= rememberCoroutineScope()
    val drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
    val selected=remember{
        mutableStateOf(Icons.Default.Home)
    }
    val sheetState= rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    homeViewModel.getUserData()

    ModalNavigationDrawer(
        drawerState=drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerHeader(homeViewModel.emailId.value)
                NavigationDrawerItem(
                    label = {Text(text="Home")},
                    selected =false ,
                    icon={Icon(imageVector = Icons.Filled.Home,contentDescription = "home")},
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        AppRouter.navigateTo(Screen.HomeScreen)

                    }
                )
                NavigationDrawerItem(
                    label = {Text(text="Profile")},
                    selected =false ,
                    icon={Icon(imageVector = Icons.Filled.Person,contentDescription = "person")},
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        AppRouter.navigateTo(Screen.Profile)

                    }
                )
                NavigationDrawerItem(
                    label = {Text(text="Setting")},
                    selected =false ,
                    icon={Icon(imageVector = Icons.Filled.Settings,contentDescription = "Setting")},
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        AppRouter.navigateTo(Screen.Settings)
                    }
                )
                NavigationDrawerItem(
                    label = {Text(text="About")},
                    selected =false ,
                    icon={Icon(imageVector = Icons.Filled.Info,contentDescription = "About")},
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        AppRouter.navigateTo(Screen.AboutScreen)
                    }
                )
                NavigationDrawerItem(
                    label = {Text(text="Logout")},
                    selected =false ,
                    icon={Icon(imageVector = Icons.Filled.Logout,contentDescription = "Logout")},
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        Toast.makeText(context,"Logging Out",Toast.LENGTH_SHORT).show()
                        homeViewModel.logout()
                    }
                )
            }
        },
    ){
        Scaffold(
            topBar = {
                AppToolbar(toolbarTitle = stringResource(id = R.string.cms),
                    logoutButtonClicked = {
                        homeViewModel.logout()
                    },
                    navigationIconClicked = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    },
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Primary
                ) {
                    IconButton(
                        onClick = { selected.value=Icons.Default.Home
//                            AppRouter.navigateTo(Screen.HomeScreen)
                                  },
                        modifier= Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = null,
                            modifier=Modifier.size(30.dp),
                            tint=if(selected.value==Icons.Default.Home) Color.Black else Color.White
                        )
                    }
                    IconButton(
                        onClick = { selected.value=Icons.Default.Search
                            AppRouter.navigateTo(Screen.SearchScreen)
                                  },
                        modifier= Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            modifier=Modifier.size(30.dp),
                            tint=if(selected.value==Icons.Default.Search) Color.Black else Color.White
                        )
                    }
                    Box(
                        modifier= Modifier
                            .weight(1f)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                    ){
                        FloatingActionButton(
                            onClick = {
                                showBottomSheet=true
                            }) {
                            Icon(imageVector = Icons.Filled.Add,contentDescription = null)
                        }
                    }
                    IconButton(
                        onClick = { selected.value=Icons.Default.Settings
                            AppRouter.navigateTo(Screen.Settings)
                                  },
                        modifier= Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null,
                            modifier=Modifier.size(30.dp),
                            tint=if(selected.value==Icons.Default.Settings) Color.Black else Color.White
                        )
                    }
                    IconButton(
                        onClick = { selected.value=Icons.Default.Person
                            AppRouter.navigateTo(Screen.Profile)
                                  },
                        modifier= Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            modifier=Modifier.size(30.dp),
                            tint=if(selected.value==Icons.Default.Person) Color.Black else Color.White
                        )
                    }

                }
            },
        ) {
            Home()
//            Column {
//                when (selected.value) {
//                    Icons.Default.Home -> Home()
//                    Icons.Default.Search -> SearchScreen()
//                    Icons.Default.Settings -> SettingsScreen()
//                    Icons.Default.Person->Profile()
//                }
//            }
        }

        if(showBottomSheet){
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState=sheetState
            ) {
                Column(
                    modifier=Modifier.fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    BottomSheetItem(icon=Icons.Default.MeetingRoom,"Meeting"){
                        showBottomSheet=false
                        AppRouter.navigateTo(Screen.Meeting)
                    }
                    BottomSheetItem(icon=Icons.Default.Schedule,"Schedule"){
                        showBottomSheet=false
                        AppRouter.navigateTo(Screen.ScheduleScreen)
                    }

                }
            }
        }
//        SystemBackButtonHandler {
//            if (AppRouter.currentScreen.value == Screen.HomeScreen) {
//                // If currently on the home screen, exit the app
//                val context = LocalContext.current
//                (context as? Activity)?.finish()
//            } else {
//                AppRouter.navigateTo(Screen.HomeScreen)
//            }
//        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}