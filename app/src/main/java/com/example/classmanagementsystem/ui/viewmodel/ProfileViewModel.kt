package com.example.classmanagementsystem.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classmanagementsystem.utils.DataStoreManager
import com.example.classmanagementsystem.ui.screens.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {
    private val _user = MutableStateFlow(
        User(
            name = "Abby Green",
            email = "abby.green@example.com",
            designation = "Software Engineer",
            profilePictureUrl = "https://via.placeholder.com/150"
        )
    )

    val user: StateFlow<User> get() = _user

    init {
        viewModelScope.launch {
            dataStoreManager.profilePictureUrl.collect { url ->
                url?.let {
                    _user.value = _user.value.copy(profilePictureUrl = it)
                }
            }
        }
    }

    fun updateUserProfile(newName: String, newDesignation: String) {
        _user.value = _user.value.copy(name = newName, designation = newDesignation)
    }

    fun updateUserProfilePicture(newProfilePictureUrl: String) {
        _user.value = _user.value.copy(profilePictureUrl = newProfilePictureUrl)
        viewModelScope.launch {
            dataStoreManager.saveProfilePictureUrl(newProfilePictureUrl)
        }
    }
}