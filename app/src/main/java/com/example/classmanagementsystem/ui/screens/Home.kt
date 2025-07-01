package com.example.classmanagementsystem.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.ui.components.FileTopBar
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    val screenColor = Color(0xFFEDE7F8)
    Scaffold(
        topBar = {
            FileTopBar(
                toolbarTitle = stringResource(id = R.string.home),
                navigationIconClicked = {
                    AppRouter.navigateTo(Screen.HomeScreen)
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = screenColor)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()

                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxSize()
                    .background(color = Color(0xFFEDE7F8)),
//                    .padding(innerPadding)
//                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .clickable { }
                    )

                    Column(
                        modifier = Modifier
                            .height(100.dp)
                            .padding(start = 14.dp)
                            .weight(0.7f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Greetings",
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,

                            )

                        Text(
                            text = "Ms. Abby Green",
                            color = Color.Black,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(top = 14.dp)
                        )

                    }
                }

                var text by rememberSaveable { mutableStateOf(" ") }

                TextField(
                    value = text, onValueChange = {
                        text = it
                    },
                    label = { Text(text = "Searching for...") },
                    trailingIcon = {
                        Box(
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .height(35.dp)
                                .width(35.dp)
//                                .background(color = Color(0xfffe5b52))
                        )

                        Image(
                            painter = painterResource(id = R.drawable.searchbar),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 6.dp)

                        )
                    },

                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
//                  backgroundColor = Color.White,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
//                  textColor = Color(android.graphics.Color.parseColor("5e5e5e")),
                        unfocusedLabelColor = Color(0xff5e5e5e)
                    ),

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .background(Color.White, CircleShape)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Column(
                        Modifier
                            .weight(0.5f)
                            .padding(end = 12.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .height(65.dp)
                                .width(75.dp)
                                .background(
                                    color = Color(0xff7868e5),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.todayschedule),
                                contentDescription = null,
                                modifier=Modifier.size(40.dp)
                                    .clickable {
                                        AppRouter.navigateTo(Screen.TodayScheduleScreen)
                                    }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .height(40.dp)
                                .background(
                                    color = Color(0xff0ad8ff),
                                    shape = RoundedCornerShape(
                                        bottomEnd = 20.dp,
                                        bottomStart = 20.dp
                                    )
                                ), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Today's Schedule",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff7868e5)
                            )
                        }
                    }

                    Column(
                        Modifier
                            .weight(0.5f)
                            .padding(start = 12.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .height(65.dp)
                                .width(75.dp)
                                .background(
                                    color = Color(0xff7868e5),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.work),
                                contentDescription = null,
                                modifier=Modifier.size(40.dp)
                                    .clickable {
                                        AppRouter.navigateTo(Screen.TimetableScreen)
                                    }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .height(40.dp)
                                .background(
                                    color = Color(0xff0ad8ff),
                                    shape = RoundedCornerShape(
                                        bottomEnd = 20.dp,
                                        bottomStart = 20.dp
                                    )
                                ), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Timetable",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff7868e5),
                            )
                        }
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp)
                ) {
                    Column(
                        Modifier
                            .weight(0.5f)
                            .padding(end = 12.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .height(65.dp)
                                .width(75.dp)
                                .background(
                                    color = Color(0xff7868e5),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.classroom),
                                contentDescription = null,
                                modifier=Modifier.size(40.dp)
                                    .clickable {
                                        AppRouter.navigateTo(Screen.ClassroomScreen)
                                    }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .height(40.dp)
                                .background(
                                    color = Color(0xff0ad8ff),
                                    shape = RoundedCornerShape(
                                        bottomEnd = 20.dp,
                                        bottomStart = 20.dp
                                    )
                                ), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Classroom",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff7868e5),

                                )
                        }
                    }

                    Column(
                        Modifier
                            .weight(0.5f)
                            .padding(start = 12.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .height(65.dp)
                                .width(75.dp)
                                .background(
                                    color = Color(0xff7868e5),
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.faculties),
                                contentDescription = null,
                                modifier=Modifier.size(40.dp)
                                    .clickable {
                                        AppRouter.navigateTo(Screen.FacultiesScreen)
                                    }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .height(40.dp)
                                .background(
                                    color = Color(0xff0ad8ff),
                                    shape = RoundedCornerShape(
                                        bottomEnd = 20.dp,
                                        bottomStart = 20.dp
                                    )
                                ), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Faculties",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xff7868e5),
                            )
                        }
                    }
                }
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .height(120.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xffBFA3EF),
                                    Color(0xff7E57c2)
                                )
                            ), shape = RoundedCornerShape(25.dp)
                        )
                ) {
                    val (img, text1, text2) = createRefs()

                    Image(
                        painter = painterResource(id = R.drawable.arc),
                        contentDescription = " ",
                        modifier = Modifier.constrainAs(img) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                    )

                    Text(
                        text = "Time Table",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .constrainAs(text1) {
                                top.linkTo(parent.top)
                                start.linkTo(img.end)
                                end.linkTo(parent.end)
                            }
                            .clickable{
                                AppRouter.navigateTo(Screen.HomeTimeTable)
                            }
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun HomePreview() {
    Home()
}