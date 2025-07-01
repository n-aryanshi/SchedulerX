package com.example.classmanagementsystem.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class Teacher(
    val name: String = "",
    val subject: String = "",
    val designation: String =""
)

class TeacherViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    private val teachersCollection = firestore.collection("teachers")

    fun addTeacherManually() {
        // Create instances of Teacher and add them to Firestore
        val teacher1 = Teacher(name = "Dr Bhawna Narwal", subject = "DFS", designation = "Assistant Professor")
        val teacher2 = Teacher(name = "Dr Ankita", subject = "Java", designation = "Assistant Professor")
        val teacher3 = Teacher(name = "Dr Nidhi Arora", subject = "Operating System", designation = "Assistant Professor")
        val teacher4 = Teacher(name = "Dr Arun Sharma", subject = "DBMS", designation = "Assistant Professor,DEAN")
        val teacher5 = Teacher(name = "Dr A.K. Mohapatra", subject = "Cyber Security", designation = "Assistant Professor,HOD")
        val teacher6 = Teacher(name = "Dr Nonita Sharma", subject = "Python", designation = "Assistant Professor")
        val teacher7 = Teacher(name = "Ms. Ipshita", subject = "Machine Learning", designation = "Phd Scholar")
        val teacher8 = Teacher(name = "Ms. Manu Shree", subject = "IT Workshop", designation = "Phd Scholar")
        val teacher9 = Teacher(name = "Dr Renu", subject = "Human Values", designation = "Assistant Professor")
        val teacher10 = Teacher(name = "Dr. Shalini Arora", subject = "Mathematics", designation = "Assistant Professor")
        // Add teachers to Firestore
        addTeacher(teacher1)
        addTeacher(teacher2)
        addTeacher(teacher3)
        addTeacher(teacher4)
        addTeacher(teacher5)
        addTeacher(teacher6)
        addTeacher(teacher7)
        addTeacher(teacher8)
        addTeacher(teacher9)
        addTeacher(teacher10)
    }
    private fun addTeacher(teacher: Teacher) {
        // Check if the teacher already exists in Firestore
        firestore.collection("teachers")
            .whereEqualTo("name", teacher.name)
            .get()
            .addOnSuccessListener { documents ->
                // If the teacher does not exist, add them to Firestore
                if (documents.isEmpty) {
                    viewModelScope.launch {
                        firestore.collection("teachers").add(teacher)
                            .addOnSuccessListener {
                                // Data added successfully
                                println("Teacher added successfully with ID: ${it.id}")
                            }
                            .addOnFailureListener { e ->
                                // Error occurred while adding data
                                println("Error adding teacher: $e")
                            }
                    }
                } else {
                    // Teacher already exists
                    val existingTeacherDocument = documents.documents.firstOrNull()
                    existingTeacherDocument?.let { existingDocument ->
                        val existingTeacherId = existingDocument.id
                        val existingTeacherName = existingDocument["name"] as String
                        // Compare existing teacher's name with the new name
                        if (existingTeacherName == teacher.name) {
                            // Update the existing document with the new teacher data
                            viewModelScope.launch {
                                firestore.collection("teachers").document(existingTeacherId)
                                    .set(teacher)
                                    .addOnSuccessListener {
                                        // Data updated successfully
                                        println("Teacher updated successfully with ID: $existingTeacherId")
                                    }
                                    .addOnFailureListener { e ->
                                        // Error occurred while updating data
                                        println("Error updating teacher: $e")
                                    }
                            }
                        } else {
                            // Name has changed, handle separately (e.g., display a message)
                            println("Cannot change teacher's name. Please update the existing record.")
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                // Error occurred while checking for existing teacher
                println("Error checking for existing teacher: $e")
            }
    }

    suspend fun getTeachers(): Flow<List<Teacher>> = flow {
        try {
            val teachersList = mutableListOf<Teacher>()
            val querySnapshot = teachersCollection.get().await()
            for (document in querySnapshot.documents) {
                val teacher = document.toObject(Teacher::class.java)
                teacher?.let { teachersList.add(it) }
            }
            emit(teachersList)
        } catch (e: Exception) {
            // Handle errors here
        }
    }

}
