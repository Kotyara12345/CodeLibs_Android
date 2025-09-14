package com.codelibs.core_network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDTO(
    val id: Int,
    @param:Json(name = "first_name") val firstName: String?,
    @param:Json(name = "last_name") val lastName: String?,
    val username: String,
    @param:Json(name = "send_messages") val sendMessages: Boolean?,
    val avatar: String?,
    val email: String?
)