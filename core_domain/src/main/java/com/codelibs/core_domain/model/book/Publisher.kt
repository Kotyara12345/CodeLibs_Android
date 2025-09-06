package com.codelibs.core_domain.model.book

data class Publisher(
    val id: Int,
    val name: String,
    val description: String?,
    val url: String?,
    val image: String?
)