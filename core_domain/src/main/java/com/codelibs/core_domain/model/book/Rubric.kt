package com.codelibs.core_domain.model.book

data class Rubric(
    val id: Int,
    val name: String,
    val slug: String,
    val description: String?
)