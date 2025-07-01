package com.example.classmanagementsystem.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("user_preferences")

class DataStoreManager(private val context: Context) {

    companion object {
        val PROFILE_PICTURE_URL_KEY = stringPreferencesKey("profile_picture_url")
    }

    val profilePictureUrl: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[PROFILE_PICTURE_URL_KEY]
        }

    suspend fun saveProfilePictureUrl(url: String) {
        context.dataStore.edit { preferences ->
            preferences[PROFILE_PICTURE_URL_KEY] = url
        }
    }
}
