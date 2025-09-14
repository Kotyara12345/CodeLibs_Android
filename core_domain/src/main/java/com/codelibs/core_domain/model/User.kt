package com.codelibs.core_domain.model

data class User(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val username: String,
    val sendMessages: Boolean = false,
    val avatarUrl: String?,
    val email: String?
)