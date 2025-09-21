package com.codelibs.core_ui.utils

import java.util.Locale

fun Int.toReadableFileSize(locale: Locale): String {
    return if (this < 1024) {
        "$this KB"
    } else {
        val mb = this / 1024.0
        String.format(locale, "%.1f MB", mb)
    }
}