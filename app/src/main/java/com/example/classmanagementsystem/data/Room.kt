package com.example.classmanagementsystem.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class Room(
    val roomNo: String = "",
    val block:String="",
)

class RoomViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    private val roomCollection = firestore.collection("room")

    fun addRoomManually() {
        // Create instances of room and add them to Firestore
        val room1 = Room(roomNo = "101", block = "IT")
        val room2 = Room(roomNo = "102", block = "IT")
        val room3 = Room(roomNo = "103", block = "IT")
        val room4 = Room(roomNo = "104", block = "IT")
        val room5 = Room(roomNo = "105", block = "IT")
        val room6 = Room(roomNo = "201", block = "IT")
        val room7 = Room(roomNo = "202", block = "IT")
        val room8 = Room(roomNo = "203", block = "IT")
        val room9 = Room(roomNo = "204", block = "IT")
        val room10 = Room(roomNo = "205", block = "IT")
        val room11 = Room(roomNo = "301", block = "IT")
        val room12 = Room(roomNo = "302", block = "IT")
        val room13 = Room(roomNo = "303", block = "IT")
        val room14 = Room(roomNo = "304", block = "IT")
        val room15 = Room(roomNo = "305", block = "IT")

        // Add teachers to Firestore
        addRoom(room1)
        addRoom(room2)
        addRoom(room3)
        addRoom(room4)
        addRoom(room5)
        addRoom(room6)
        addRoom(room7)
        addRoom(room8)
        addRoom(room9)
        addRoom(room10)
        addRoom(room11)
        addRoom(room12)
        addRoom(room13)
        addRoom(room14)
        addRoom(room15)

    }
    private fun addRoom(room: Room) {
        // Check if the teacher already exists in Firestore
        firestore.collection("room")
            .whereEqualTo("roomNo", room.roomNo)
            .get()
            .addOnSuccessListener { documents ->
                // If the teacher does not exist, add them to Firestore
                if (documents.isEmpty) {
                    viewModelScope.launch {
                        firestore.collection("room").add(room)
                            .addOnSuccessListener {
                                // Data added successfully
                                println("Classroom added successfully with ID: ${it.id}")
                            }
                            .addOnFailureListener { e ->
                                // Error occurred while adding data
                                println("Error adding classroom: $e")
                            }
                    }
                } else {

                    val existingTeacherDocument = documents.documents.firstOrNull()
                    existingTeacherDocument?.let { existingDocument ->
                        val existingRoomId = existingDocument.id
                        val existingRoomNo = existingDocument["roomNo"] as String
                        // Compare existing room number with the new name
                        if (existingRoomNo == room.roomNo) {
                            // Update the existing document with the new teacher data
                            viewModelScope.launch {
                                firestore.collection("room").document(existingRoomId)
                                    .set(room)
                                    .addOnSuccessListener {
                                        // Data updated successfully
                                        println("Room Number updated successfully with ID: $existingRoomId")
                                    }
                                    .addOnFailureListener { e ->
                                        // Error occurred while updating data
                                        println("Error updating room number: $e")
                                    }
                            }
                        } else {
                            // Name has changed, handle separately (e.g., display a message)
                            println("Cannot change room number. Please update the existing record.")
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                // Error occurred while checking for existing roomNo
                println("Error checking for existing room number: $e")
            }
    }

    suspend fun getRoom(): Flow<List<Room>> = flow {
        try {
            val roomsList = mutableListOf<Room>()
            val querySnapshot = roomCollection.get().await()
            for (document in querySnapshot.documents) {
                val room = document.toObject(Room::class.java)
                room?.let { roomsList.add(it) }
            }
            emit(roomsList)
        } catch (e: Exception) {
            // Handle errors here
        }
    }

}
