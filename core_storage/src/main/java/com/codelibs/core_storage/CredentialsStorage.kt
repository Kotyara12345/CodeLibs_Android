package com.codelibs.core_storage

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class CredentialsStorage(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPrefs = EncryptedSharedPreferences.create(
        context,
        "credentials_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveCredentials(login: String, password: String) {
        sharedPrefs.edit {
            putString("login", login)
            putString("password", password)
        }
    }

    fun getLogin(): String? = sharedPrefs.getString("login", null)
    fun getPassword(): String? = sharedPrefs.getString("password", null)

    fun clear() {
        sharedPrefs.edit { clear() }
    }
}