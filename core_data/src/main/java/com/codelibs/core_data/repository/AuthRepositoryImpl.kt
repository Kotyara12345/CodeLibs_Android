package com.codelibs.core_data.repository

import com.codelibs.core_data.mapper.toDomain
import com.codelibs.core_domain.model.AccountMini
import com.codelibs.core_domain.model.User
import com.codelibs.core_domain.repository.AuthRepository
import com.codelibs.core_network.api.BooksApiService
import com.codelibs.core_network.dto.SessionRequestDTO
import com.codelibs.core_storage.CredentialsStorage
import com.codelibs.core_storage.UserPreferences
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: BooksApiService,
    private val userPrefs: UserPreferences,
    private val credentials: CredentialsStorage
) : AuthRepository {
    override suspend fun login(username: String, password: String): AccountMini {
        val dto = api.createSession(SessionRequestDTO(username, password)).toDomain()

        // сохраняем креды
        credentials.saveCredentials(username, password)

        // сохраняем данные юзера
        userPrefs.saveUser(dto.id, dto.username)
        return dto
    }

    override suspend fun logout() {
        credentials.clear()
        userPrefs.clearUser()
    }

    override suspend fun getAccount(id: Int): User {
        return api.getAccount(id).toDomain()
    }
}