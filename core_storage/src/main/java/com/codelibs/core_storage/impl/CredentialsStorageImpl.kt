@file:Suppress("DEPRECATION")

package com.codelibs.core_storage.impl

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.codelibs.core_storage.repository.CredentialsStorage

class CredentialsStorageImpl(context: Context): CredentialsStorage {
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

    override fun saveCredentials(login: String, password: String) {
        sharedPrefs.edit {
            putString("login", login)
            putString("password", password)
        }
    }

    override fun getLogin(): String? = sharedPrefs.getString("login", null)
    override fun getPassword(): String? = sharedPrefs.getString("password", null)

    override fun clear() {
        sharedPrefs.edit { clear() }
    }
}