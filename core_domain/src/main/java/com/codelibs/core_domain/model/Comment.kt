package com.codelibs.core_domain.model

import java.time.OffsetDateTime

data class Comment(
    val id: Int,
    val user: AccountMini,
    val content: String,
    val createdAt: OffsetDateTime?,
    val updatedAt: OffsetDateTime?,
    val answerTo: String?,
    val isActive: Boolean
)