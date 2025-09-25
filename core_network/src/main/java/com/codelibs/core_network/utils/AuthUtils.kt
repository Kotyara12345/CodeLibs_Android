package com.codelibs.core_network.utils

import android.util.Base64

fun buildBasicAuthHeader(username: String, password: String): String {
    val credentials = "$username:$password"
    val encoded = Base64.encodeToString(credentials.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
    return "Basic $encoded"
}