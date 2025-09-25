package com.codelibs.core_domain.repository

import com.codelibs.core_domain.model.AccountMini
import com.codelibs.core_domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): AccountMini
    suspend fun logout()
    suspend fun getAccount(id: Int): User
}