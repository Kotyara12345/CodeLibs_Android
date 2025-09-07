package com.codelibs.core_data.model.book

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResponse(
    val id: Int,
    val rubrics: List<RubricResponse>,
    val authors: List<AuthorResponse>,
    val publisher: PublisherResponse,
    @param:Json(name = "lang_category") val langCategory: String?,
    @param:Json(name = "file_format") val fileFormat: String?,
    @param:Json(name = "added_by") val addedBy: AddedByResponse,
    val rating: String?,
    @param:Json(name = "is_favorited") val isFavorited: String?,
    val title: String,
    val url: String,
    @param:Json(name = "year_release") val yearRelease: Int?,
    val translator: String?,
    val content: String,
    @param:Json(name = "table_of_contents") val tableOfContents: String?,
    @param:Json(name = "pages_number") val pagesNumber: Int?,
    val image: String?,
    @param:Json(name = "book_file") val bookFile: String?,
    @param:Json(name = "file_slug") val fileSlug: String,
    @param:Json(name = "add_files") val addFiles: String
)
