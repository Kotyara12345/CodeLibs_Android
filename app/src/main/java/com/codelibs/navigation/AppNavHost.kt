package com.codelibs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hsact.feature_bookpage.ui.BookPageScreen
import com.hsact.feature_catalog.ui.CatalogScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppDestination.Home.route,
        modifier = modifier
    ) {
        // Главная
        composable(AppDestination.Home.route) {
            CatalogScreen(
                onItemClick = { bookId ->
                    navController.navigate(AppDestination.BookPage.createRoute(bookId))
                }
            )
        }

        // Категории
        composable(AppDestination.Categories.route) {
            // TODO: CategoriesScreen из отдельного модуля
        }

        // Избранное
        composable(AppDestination.Favorites.route) {
            CatalogScreen(
                onItemClick = { bookId ->
                    navController.navigate(AppDestination.BookPage.createRoute(bookId))
                }
            )
        }

        // Аккаунт
        composable(AppDestination.Account.route) {
            // TODO: AccountScreen из отдельного модуля
        }

        // Экран книги
        composable(
            route = AppDestination.BookPage.route,
            arguments = listOf(
                navArgument("bookId") { type = NavType.IntType }
            )
        ) {
            BookPageScreen()
        }
    }
}