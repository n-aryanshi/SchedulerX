package com.example.classmanagementsystem.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.classmanagementsystem.R
import com.example.classmanagementsystem.components.FileTopBar
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.example.classmanagementsystem.navigation.SystemBackButtonHandler

sealed class TimetableItem {
    data class Class(val className: String) : TimetableItem()
    data class Break(val breakName: String) : TimetableItem()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableScreen(){

    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    val timeSlots = listOf("9:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-1:00", "1:00-2:00", "2:00-3:00", "3:00-4:00","4:00-5:00")

    val timetableData = listOf(
        listOf(TimetableItem.Class("HVE"), TimetableItem.Class(""),TimetableItem.Class("IT workshop Lab Grp-H,ML Lab Grp-G"), TimetableItem.Class(""), TimetableItem.Class("")),
        listOf(TimetableItem.Class("HVE"), TimetableItem.Class("ML"),TimetableItem.Class("IT workshop Lab Grp-H,ML Lab Grp-G"), TimetableItem.Class("JAVA"), TimetableItem.Class("")),
        listOf(TimetableItem.Class("JAVA"), TimetableItem.Class("IT workshop"),TimetableItem.Class("DCCN"), TimetableItem.Class("JAVA"), TimetableItem.Class("ML")),
        listOf(TimetableItem.Break("LUNCH"), TimetableItem.Break("LUNCH"),TimetableItem.Break("LUNCH"), TimetableItem.Break("Lunch"), TimetableItem.Break("LUNCH")),
        listOf(TimetableItem.Class("DCCN"), TimetableItem.Class("JAVA Lab Grp-G,DCCN Lab Grp H"),TimetableItem.Class("JAVA Lab Grp-H,DCCN Lab Grp-G"), TimetableItem.Class("SE"), TimetableItem.Class("IT workshop Lab Grp-G")),
        listOf(TimetableItem.Class("DCCN"), TimetableItem.Class("JAVA Lab Grp-G,DCCN Lab Grp H"),TimetableItem.Class("JAVA Lab Grp-H,DCCN Lab Grp-g"), TimetableItem.Class("SE"), TimetableItem.Class("IT workshop Lab Grp-G")),
        listOf(TimetableItem.Class("ML LAB Grp-H"), TimetableItem.Class("SE Lab"),TimetableItem.Class("SE"), TimetableItem.Class("ML"), TimetableItem.Class("HVE")),
        listOf(TimetableItem.Class("ML LAB Grp-H"), TimetableItem.Class("SE Lab"),TimetableItem.Class(""), TimetableItem.Class(""), TimetableItem.Class("")),
    )

    Scaffold(
        topBar = {
            FileTopBar(
                toolbarTitle = stringResource(id = R.string.timetable),
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
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState()),
//                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){

            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(140.dp)) // Placeholder for empty space in the top-left corner
                    daysOfWeek.forEach { day ->
                        DayOfWeekItem(text = day)
                    }
                }

                timetableData.forEachIndexed { index, rowData ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TimeSlotItem(text = timeSlots[index])
                        rowData.forEach { item ->
                            when (item) {
                                is TimetableItem.Class -> ClassItem(text = item.className)
                                is TimetableItem.Break -> BreakItem(text = item.breakName)
                            }
                        }
                    }
                }
            }

        }
        SystemBackButtonHandler {
            AppRouter.navigateTo(Screen.HomeScreen)
        }
    }
}

@Composable
fun DayOfWeekItem(text: String) {
    Surface(
        modifier = Modifier
            .width(140.dp)
            .height(50.dp)
            .padding(2.dp)
            .border(width = 1.dp, color = Color.Gray),
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Box (
            contentAlignment = Alignment.Center
        ){
            Text(
                text = text,
                modifier = Modifier.padding(8.dp),
                color = Color.White
            )
        }
    }
}

@Composable
fun ClassItem(text: String) {
    Surface(
        modifier = Modifier
            .width(140.dp)
            .height(90.dp)
            .padding(5.dp)
            .border(width = 1.dp, color = Color.Gray),
        color = Color.White
    ) {
        Box(contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text(
                text = text
            )
        }
    }
}

@Composable
fun BreakItem(text: String) {
    Surface(
        modifier = Modifier
            .width(140.dp)
            .height(90.dp)
            .padding(4.dp)
            .border(width = 1.dp, color = Color.Gray),
        color = Color.Gray
    ) {
        Box(contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text(
                text = text,
                color = Color.White
            )
        }
    }
}
@Composable
fun TimeSlotItem(text: String) {
    Surface(
        modifier = Modifier
            .width(140.dp)
            .height(90.dp)
            .padding(5.dp)
            .border(width = 1.dp, color = Color.Gray),
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colorScheme.secondary
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(8.dp),
                color = Color.White
            )
        }
    }
}


@Preview
@Composable
fun TimetableScreenPreview() {
    TimetableScreen()
}