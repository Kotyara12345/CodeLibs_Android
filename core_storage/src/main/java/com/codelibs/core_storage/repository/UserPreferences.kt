package com.codelibs.core_storage.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val userId: Flow<Int?>
    val username: Flow<String?>
    suspend fun saveUser(id: Int, username: String)
    suspend fun clearUser()
}