package com.codelibs.core_network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentDTO(
    val id: Int,
    val user: AccountMiniDTO,
    val content: String,
    @param:Json(name = "created_at") val createdAt: String,
    @param:Json(name = "updated_at") val updatedAt: String,
    @param:Json(name = "answer_to") val answerTo: String?,
    @param:Json(name = "is_active") val isActive: Boolean
)