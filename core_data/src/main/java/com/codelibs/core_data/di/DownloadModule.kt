package com.codelibs.core_data.di

import com.codelibs.core_domain.repository.FileDownloader
import com.codelibs.core_data.repository.FileDownloaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DownloadModule {

    @Binds
    @Singleton
    abstract fun bindFileDownloader(
        impl: FileDownloaderImpl
    ): FileDownloader
}