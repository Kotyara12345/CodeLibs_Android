package com.codelibs.core_download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DownloadManagerFileDownloader @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private val downloadObserver = DownloadObserver(context)

    fun download(
        url: String,
        fileName: String,
        onStatusChange: (progress: Int, isDownloading: Boolean) -> Unit
    ): Long {
        val request = DownloadManager.Request(url.toUri())
            .setTitle(fileName)
            .setDescription("Downloading...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadId = dm.enqueue(request)

        downloadObserver.observeDownload(downloadId) { progress, isDownloading ->
            onStatusChange(progress, isDownloading)
            Log.d("FileDownloader", "Download progress: $progress%")
        }
        return downloadId
    }
}