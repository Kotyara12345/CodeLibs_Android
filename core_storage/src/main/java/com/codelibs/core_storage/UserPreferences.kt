package com.codelibs.core_storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userDataStore by preferencesDataStore("user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val USER_ID = intPreferencesKey("user_id")
        val USERNAME = stringPreferencesKey("username")
    }

    val userId: Flow<Int?> = context.userDataStore.data.map { prefs ->
        prefs[USER_ID]
    }

    val username: Flow<String?> = context.userDataStore.data.map { prefs ->
        prefs[USERNAME]
    }

    suspend fun saveUser(id: Int, username: String) {
        context.userDataStore.edit { prefs ->
            prefs[USER_ID] = id
            prefs[USERNAME] = username
        }
    }

    suspend fun clearUser() {
        context.userDataStore.edit { it.clear() }
    }
}