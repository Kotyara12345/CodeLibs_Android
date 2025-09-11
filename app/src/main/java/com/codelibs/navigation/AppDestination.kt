package com.codelibs.navigation

sealed class AppDestination(val route: String) {
    // пункты нижнего меню
    data object Home : AppDestination("home")
    data object Categories : AppDestination("categories")
    data object Favorites : AppDestination("favorites")
    data object Account : AppDestination("account")

    // отдельные экраны
    data object BookPage : AppDestination("bookpage/{bookId}") {
        fun createRoute(bookId: Int) = "bookpage/$bookId"
    }
}