package com.codelibs.core_download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DownloadManagerFileDownloader @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    fun download(url: String, fileName: String) {
        val request = DownloadManager.Request(url.toUri())
            .setTitle(fileName)
            .setDescription("Downloading...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        dm.enqueue(request)
    }
}