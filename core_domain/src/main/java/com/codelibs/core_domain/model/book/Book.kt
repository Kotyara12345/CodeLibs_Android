package com.codelibs.core_domain.model.book

data class Book(
    val id: Int,
    val rubrics: List<Rubric>,
    val authors: List<Author>,
    val publisher: Publisher,
    val langCategory: String?,
    val fileFormat: String?,
    val addedBy: AddedBy,
    val rating: Int?,
    val isFavorited: Boolean,
    val title: String?,
    val url: String?,
    val yearRelease: Int?,
    val translator: String?,
    val content: String?,
    val tableOfContents: String?,
    val pagesNumber: Int?,
    val image: String?,
    val bookFile: String?,
    val fileSlug: String?,
    val addFiles: String?
)