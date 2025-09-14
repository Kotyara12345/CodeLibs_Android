package com.codelibs.navigation

import android.net.Uri

sealed class AppDestination(val route: String) {
    data object Home : AppDestination("home")
    data object Categories : AppDestination("categories")
    data object Favorites : AppDestination("favorites")
    data object Account : AppDestination("account")

    data object BookPage : AppDestination("bookpage/{bookId}") {
        fun createRoute(bookId: Int) = "bookpage/$bookId"
    }

    object Catalog : AppDestination("catalog/{rubricId}/{rubricName}") {
        fun createRoute(rubricId: Int, rubricName: String) =
            "catalog/$rubricId/${Uri.encode(rubricName)}"
    }
}