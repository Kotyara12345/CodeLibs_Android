package com.codelibs.core_download

import android.app.DownloadManager
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DownloadObserver(
    private val context: Context
) {
    fun observeDownload(downloadId: Long, onStatusChange: (isDownloading: Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var downloading = true
            while (downloading) {
                val query = DownloadManager.Query().setFilterById(downloadId)
                dm.query(query)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val statusIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                        if (statusIndex != -1) {
                            when (cursor.getInt(statusIndex)) {
                                DownloadManager.STATUS_SUCCESSFUL,
                                DownloadManager.STATUS_FAILED,
                                DownloadManager.STATUS_PAUSED -> {
                                    downloading = false
                                    onStatusChange(false)
                                }
                                DownloadManager.STATUS_RUNNING,
                                DownloadManager.STATUS_PENDING -> onStatusChange(true)
                            }
                        }
                    }
                }
                delay(200)
            }
        }
    }
}