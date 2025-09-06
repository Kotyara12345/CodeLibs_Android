package com.codelibs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hsact.feature_bookpage.ui.BookPageScreen
import com.hsact.feature_catalog.ui.CatalogScreen

sealed class AppDestination(val route: String) {
    data object Catalog : AppDestination("catalog")
    data object BookPage : AppDestination("bookpage/{bookId}") {
        fun createRoute(bookId: String) = "bookpage/$bookId"
    }
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Catalog.route,
        modifier = modifier
    ) {
        composable(AppDestination.Catalog.route) {
            CatalogScreen(
                onItemClick = { bookId ->
                    navController.navigate(AppDestination.BookPage.createRoute(bookId))
                }
            )
        }
        composable(AppDestination.BookPage.route) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")
            BookPageScreen(
                bookId!!
            )
        }
    }
}