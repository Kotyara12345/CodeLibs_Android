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
    fun observeDownload(
        downloadId: Long,
        onProgress: (progress: Int, isDownloading: Boolean) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            var downloading = true

            while (downloading) {
                val query = DownloadManager.Query().setFilterById(downloadId)
                dm.query(query)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val statusIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                        val totalIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)
                        val downloadedIndex = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)

                        if (statusIndex != -1) {
                            when (cursor.getInt(statusIndex)) {
                                DownloadManager.STATUS_SUCCESSFUL -> {
                                    downloading = false
                                    onProgress(100, false)
                                }
                                DownloadManager.STATUS_FAILED,
                                DownloadManager.STATUS_PAUSED -> {
                                    downloading = false
                                    onProgress(0, false)
                                }
                                DownloadManager.STATUS_RUNNING,
                                DownloadManager.STATUS_PENDING -> {
                                    val total = if (totalIndex != -1) cursor.getLong(totalIndex) else -1L
                                    val downloaded = if (downloadedIndex != -1) cursor.getLong(downloadedIndex) else -1L

                                    val progress = if (total > 0 && downloaded >= 0) {
                                        ((downloaded * 100L) / total).toInt().coerceIn(0, 100)
                                    } else {
                                        0
                                    }

                                    onProgress(progress, true)
                                }
                            }
                        }
                    }
                }
                delay(250)
            }
        }
    }
}