package com.codelibs.core_data.repository

import android.util.Log
import com.codelibs.core_domain.repository.FileDownloader
import com.codelibs.core_download.DownloadManagerFileDownloader
import javax.inject.Inject

class FileDownloaderImpl @Inject constructor(
    private val downloadManagerFileDownloader: DownloadManagerFileDownloader
) : FileDownloader {

    override fun download(
        url: String,
        fileName: String,
        onStatusChange: (progress: Int, isDownloading: Boolean) -> Unit
    ): Long {
        Log.d("FileDownloaderImpl", "Downloading file: $url")
        return downloadManagerFileDownloader.download(url, fileName, onStatusChange)
    }
}