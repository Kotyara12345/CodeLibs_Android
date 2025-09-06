package com.codelibs.core_data.remote.api

import com.codelibs.core_data.remote.dto.BooksResponse
import retrofit2.http.GET
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
}