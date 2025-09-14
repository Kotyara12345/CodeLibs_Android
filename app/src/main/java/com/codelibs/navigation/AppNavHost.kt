package com.codelibs.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codelibs.feature_rubrics.ui.RubricsScreen
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
        // Home (главный каталог без фильтра)
        composable(AppDestination.Home.route) {
            CatalogScreen(
                rubricId = null,
                rubricName = null,
                onItemClick = { bookId ->
                    navController.navigate(AppDestination.BookPage.createRoute(bookId))
                }
            )
        }

        // Categories (список рубрик)
        composable(AppDestination.Categories.route) {
            RubricsScreen(
                onItemClick = { rubricId, rubricName ->
                    navController.navigate(AppDestination.Catalog.createRoute(rubricId, rubricName))
                }
            )
        }

        // Catalog по рубрике (принимаем id + name)
        composable(
            route = AppDestination.Catalog.route,
            arguments = listOf(
                navArgument("rubricId") { type = NavType.IntType },
                navArgument("rubricName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val rubricId = backStackEntry.arguments?.getInt("rubricId")
            val rawName = backStackEntry.arguments?.getString("rubricName")
            val rubricName = rawName?.let { Uri.decode(it) } // безопасно декодируем
            CatalogScreen(
                rubricId = rubricId,
                rubricName = rubricName,
                onItemClick = { bookId ->
                    navController.navigate(AppDestination.BookPage.createRoute(bookId))
                }
            )
        }

        // Favorites
        composable(AppDestination.Favorites.route) {
            CatalogScreen(
                rubricId = null,
                rubricName = null,
                onItemClick = { bookId ->
                    navController.navigate(AppDestination.BookPage.createRoute(bookId))
                }
            )
        }

        // Account
        composable(AppDestination.Account.route) {
            // TODO: AccountScreen
        }

        // Book page
        composable(
            route = AppDestination.BookPage.route,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) {
            BookPageScreen()
        }
    }
}