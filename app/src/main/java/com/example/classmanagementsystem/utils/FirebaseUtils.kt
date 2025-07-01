package com.example.classmanagementsystem.utils

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class TimetableEntry(
    val subject: String = "",
    val room: String = "",
    val teacher: String = ""
)

// Fetch timetable data for a specific course and branch
fun fetchTimetable(course: String, branch: String, onComplete: (Map<String, List<TimetableEntry>>) -> Unit) {
    val database = FirebaseDatabase.getInstance().reference
    val timetableRef = database.child("timetables").child(course).child(branch)

    timetableRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val timetable = mutableMapOf<String, List<TimetableEntry>>()
            snapshot.children.forEach { daySnapshot ->
                val day = daySnapshot.key ?: return
                val timeSlots = mutableListOf<TimetableEntry>()
                daySnapshot.children.forEach { timeSlotSnapshot ->
                    val entry = timeSlotSnapshot.getValue(TimetableEntry::class.java) ?: TimetableEntry()
                    timeSlots.add(entry)
                }
                timetable[day] = timeSlots
            }
            onComplete(timetable)
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle error
        }
    })
}

// Add or update timetable entry with conflict checking
fun addOrUpdateTimetableEntry(
    course: String,
    branch: String,
    day: String,
    timeSlot: String,
    entry: TimetableEntry,
    onSuccess: () -> Unit,
    onFailure: (String) -> Unit
) {
    val database = FirebaseDatabase.getInstance().reference
    val roomRef = database.child("rooms").child(entry.room).child(day).child(timeSlot)
    val teacherRef = database.child("teachers").child(entry.teacher).child(day).child(timeSlot)

    roomRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(roomSnapshot: DataSnapshot) {
            if (roomSnapshot.exists() && roomSnapshot.value != branch) {
                onFailure("Room conflict detected")
                return
            }

            teacherRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(teacherSnapshot: DataSnapshot) {
                    if (teacherSnapshot.exists() && teacherSnapshot.value != branch) {
                        onFailure("Teacher conflict detected")
                        return
                    }

                    val timetableRef = database.child("timetables").child(course).child(branch).child(day).child(timeSlot)
                    timetableRef.setValue(entry).addOnSuccessListener {
                        roomRef.setValue(branch)
                        teacherRef.setValue(branch)
                        onSuccess()
                    }.addOnFailureListener {
                        onFailure(it.message ?: "Error adding timetable entry")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onFailure(error.message)
                }
            })
        }

        override fun onCancelled(error: DatabaseError) {
            onFailure(error.message)
        }
    })
}
