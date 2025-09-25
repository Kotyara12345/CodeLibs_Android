package com.codelibs.core_storage.repository

interface CredentialsProvider {
    fun saveCredentials(login: String, password: String)
    fun getLogin(): String?
    fun getPassword(): String?
    fun clear()
}