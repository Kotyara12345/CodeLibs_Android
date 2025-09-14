package com.codelibs.core_network.api

import com.codelibs.core_network.dto.BookDTO
import com.codelibs.core_network.dto.BooksResponse
import com.codelibs.core_network.dto.RubricDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {

    @GET("books/")
    suspend fun getBooks(
        @Query("page") page: Int = 1,
        @Query("rubrics") rubrics: List<Int>? = null,
        @Query("authors") authors: List<Int>? = null,
        @Query("lang_category") langCategory: String? = null,
        @Query("year_release") yearRelease: Int? = null
    ): BooksResponse

    @GET("books/{id}")
    suspend fun getBook(
        @Path("id") id: Int
    ): BookDTO

    @GET("rubrics/")
    suspend fun getRubrics(
        @Query("search") search: String? = null,
    ): List<RubricDTO>
}