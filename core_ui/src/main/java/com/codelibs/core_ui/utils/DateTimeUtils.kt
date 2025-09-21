package com.codelibs.core_ui.utils

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun OffsetDateTime.toReadableDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    return this.format(formatter)
}