package com.codelibs.core_domain.usecase

import com.codelibs.core_domain.repository.FileDownloader

class DownloadBookUseCase(
    private val fileDownloader: FileDownloader
) {
    operator fun invoke(url: String, fileName: String) {
        fileDownloader.download(url, fileName)
    }
}