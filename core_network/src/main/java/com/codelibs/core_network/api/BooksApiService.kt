package com.codelibs.core_network.api

import com.codelibs.core_network.dto.AccountMiniDTO
import com.codelibs.core_network.dto.BookDTO
import com.codelibs.core_network.dto.BooksResponse
import com.codelibs.core_network.dto.CommentDTO
import com.codelibs.core_network.dto.SessionRequestDTO
import com.codelibs.core_network.dto.RubricDTO
import com.codelibs.core_network.dto.UserDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {

    // Авторизация
    @POST("accounts/session/")
    suspend fun createSession(
        @Body request: SessionRequestDTO
    ): AccountMiniDTO

    @GET("accounts/{id}/")
    suspend fun getAccount(
        @Path("id") id: Int
    ): UserDTO

    @GET("books/")
    suspend fun getBooks(
        @Query("page") page: Int = 1,
        @Query("rubrics") rubrics: List<Int>? = null,
        @Query("authors") authors: List<Int>? = null,
        @Query("lang_category") langCategory: String? = null,
        @Query("year_release") yearRelease: Int? = null
    ): BooksResponse

    @GET("books/{id}/")
    suspend fun getBook(
        @Path("id") id: Int
    ): BookDTO

    @GET("rubrics/")
    suspend fun getRubrics(
        @Query("search") search: String? = null,
    ): List<RubricDTO>

    @GET("books/{id}/similar_books/")
    suspend fun getSimilarBooks(
        @Path("id") bookId: Int,
    ): List<BookDTO>

    @GET("books/{book_id}/comments/")
    suspend fun getComments(
        @Path("book_id") bookId: Int,
    ): List<CommentDTO>
}