package com.codelibs.core_data.di

import android.content.Context
import com.codelibs.core_storage.CredentialsStorage
import com.codelibs.core_storage.UserPreferences
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
        UserPreferences(context)

    @Provides
    @Singleton
    fun provideCredentialsStorage(@ApplicationContext context: Context): CredentialsStorage =
        CredentialsStorage(context)
}