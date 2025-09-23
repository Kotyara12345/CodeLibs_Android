package com.codelibs.core_data.repository

import com.codelibs.core_domain.repository.FileDownloader
import com.codelibs.core_download.DownloadManagerFileDownloader
import javax.inject.Inject

class FileDownloaderImpl @Inject constructor(
    private val downloadManagerFileDownloader: DownloadManagerFileDownloader
) : FileDownloader {

    override fun download(url: String, fileName: String) {
        downloadManagerFileDownloader.download(url, fileName)
    }
}