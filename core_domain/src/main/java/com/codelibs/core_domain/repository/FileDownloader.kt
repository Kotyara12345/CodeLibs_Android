package com.codelibs.core_domain.repository

interface FileDownloader {
    /**
     * Скачивает файл по ссылке и сохраняет во внутреннее хранилище
     *
     * @param url      - ссылка на файл
     * @param fileName - имя файла (например, "book.pdf")
     */
    fun download(
        url: String,
        fileName: String,
        onStatusChange: (isDownloading: Boolean) -> Unit
    ): Long // возвращаем downloadId
}