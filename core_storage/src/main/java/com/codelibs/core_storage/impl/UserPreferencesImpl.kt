package com.codelibs.core_storage.impl

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.codelibs.core_storage.repository.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userDataStore by preferencesDataStore("user_prefs")

class UserPreferencesImpl(private val context: Context): UserPreferences {

    companion object {
        val USER_ID = intPreferencesKey("user_id")
        val USERNAME = stringPreferencesKey("username")
    }

    override val userId: Flow<Int?> = context.userDataStore.data.map { prefs ->
        prefs[USER_ID]
    }

    override val username: Flow<String?> = context.userDataStore.data.map { prefs ->
        prefs[USERNAME]
    }

    override suspend fun saveUser(id: Int, username: String) {
        context.userDataStore.edit { prefs ->
            prefs[USER_ID] = id
            prefs[USERNAME] = username
        }
    }

    override suspend fun clearUser() {
        context.userDataStore.edit { it.clear() }
    }
}