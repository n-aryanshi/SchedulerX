package com.example.classmanagementsystem.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.classmanagementsystem.data.RegistrationUIState
import com.example.classmanagementsystem.data.rules.Validator
import com.example.classmanagementsystem.navigation.AppRouter
import com.example.classmanagementsystem.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//class SignupViewModel: ViewModel() {
//
//    private val TAG = SignupViewModel::class.simpleName
//
//
//    var registrationUIState = mutableStateOf(RegistrationUIState())
//
//    var allValidationsPassed = mutableStateOf(false)
//
//    var signUpInProgress = mutableStateOf(false)
//
//
//    fun onEvent(event: SignupUIEvent) {
//        when (event) {
//            is SignupUIEvent.FirstNameChanged -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    firstName = event.firstName
//                )
//                printState()
//            }
//
//            is SignupUIEvent.LastNameChanged -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    lastName = event.lastName
//                )
//                printState()
//            }
//
//            is SignupUIEvent.EmailChanged -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    email = event.email
//                )
//                printState()
//
//            }
//
//
//            is SignupUIEvent.PasswordChanged -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    password = event.password
//                )
//                printState()
//
//            }
//
//            is SignupUIEvent.RegisterButtonClicked -> {
//                signUp()
//            }
//
//            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
//                registrationUIState.value = registrationUIState.value.copy(
//                    privacyPolicyAccepted = event.status
//                )
//            }
//        }
//        validateDataWithRules()
//    }
//
//
//    private fun signUp() {
//        Log.d(TAG, "Inside_signUp")
//        printState()
//        createUserInFirebase(
//            email = registrationUIState.value.email,
//            password = registrationUIState.value.password
//        )
//    }
//
//    private fun validateDataWithRules() {
//        val fNameResult = Validator.validateFirstName(
//            fName = registrationUIState.value.firstName
//        )
//
//        val lNameResult = Validator.validateLastName(
//            lName = registrationUIState.value.lastName
//        )
//
//        val emailResult = Validator.validateEmail(
//            email = registrationUIState.value.email
//        )
//
//
//        val passwordResult = Validator.validatePassword(
//            password = registrationUIState.value.password
//        )
//
//        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
//            statusValue = registrationUIState.value.privacyPolicyAccepted
//        )
//
//
//        Log.d(TAG, "Inside_validateDataWithRules")
//        Log.d(TAG, "fNameResult= $fNameResult")
//        Log.d(TAG, "lNameResult= $lNameResult")
//        Log.d(TAG, "emailResult= $emailResult")
//        Log.d(TAG, "passwordResult= $passwordResult")
//        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")
//
//        registrationUIState.value = registrationUIState.value.copy(
//            firstNameError = fNameResult.status,
//            lastNameError = lNameResult.status,
//            emailError = emailResult.status,
//            passwordError = passwordResult.status,
//            privacyPolicyError = privacyPolicyResult.status
//        )
//
//
//        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
//                emailResult.status && passwordResult.status && privacyPolicyResult.status
//
//    }
//
//
//    private fun printState() {
//        Log.d(TAG, "Inside_printState")
//        Log.d(TAG, registrationUIState.value.toString())
//    }
//
//
//    private fun createUserInFirebase(email: String, password: String) {
//
//        signUpInProgress.value = true
//
//        FirebaseAuth
//            .getInstance()
//            .createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener {
//                Log.d(TAG, "Inside_OnCompleteListener")
//                Log.d(TAG, " isSuccessful = ${it.isSuccessful}")
//
//                signUpInProgress.value = false
//                if (it.isSuccessful) {
//                    AppRouter.navigateTo(Screen.HomeScreen)
//                }
//            }
//            .addOnFailureListener {
//                Log.d(TAG, "Inside_OnFailureListener")
//                Log.d(TAG, "Exception= ${it.message}")
//                Log.d(TAG, "Exception= ${it.localizedMessage}")
//            }
//    }
//
//}
class SignupViewModel: ViewModel() {

    private val TAG = SignupViewModel::class.simpleName
    private val db = Firebase.firestore
    private val usersCollection = db.collection("users")

    var registrationUIState = mutableStateOf(RegistrationUIState())
    var allValidationsPassed = mutableStateOf(false)
    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignupUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }


            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
        validateDataWithRules()
    }


    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )


        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )

        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status

    }


    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }


    private fun createUserInFirebase(email: String, password: String) {

        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                    authResult ->
                signUpInProgress.value = false
                if (authResult.isSuccessful) {
                    // Additional user data to store in Firestore
                    val userData = hashMapOf(
                        "firstName" to registrationUIState.value.firstName,
                        "lastName" to registrationUIState.value.lastName,
                        "email" to registrationUIState.value.email,
                        "password" to registrationUIState.value.password,
                        "privacyPolicyAccepted" to registrationUIState.value.privacyPolicyAccepted
                    )

                    // Add user data to Firestore
                    usersCollection.document(authResult.result?.user?.uid ?: "")
                        .set(userData)
                        .addOnSuccessListener {
                            Log.d(TAG, "User data added to Firestore")
                            AppRouter.navigateTo(Screen.HomeScreen)
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "Error adding user data to Firestore", e)
                            // Handle failure
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error creating user", e)
                // Handle failure
            }
    }

}