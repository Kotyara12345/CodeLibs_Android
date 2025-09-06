package com.codelibs.core_data.di

import com.codelibs.core_data.repository.BooksRepositoryImpl
import com.codelibs.core_domain.repository.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBooksRepository(
        impl: BooksRepositoryImpl
    ): BooksRepository
}