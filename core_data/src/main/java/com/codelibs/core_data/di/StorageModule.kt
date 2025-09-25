package com.codelibs.core_data.di

import android.content.Context
import com.codelibs.core_storage.impl.CredentialsStorage
import com.codelibs.core_storage.impl.UserPreferencesImpl
import com.codelibs.core_storage.repository.CredentialsProvider
import com.codelibs.core_storage.repository.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences =
        UserPreferencesImpl(context)

    @Provides
    @Singleton
    fun provideCredentialsStorage(@ApplicationContext context: Context): CredentialsProvider =
        CredentialsStorage(context)
}