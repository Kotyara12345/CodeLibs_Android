package com.codelibs.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codelibs.feature_profile.ui.ProfileScreen
import com.codelibs.feature_rubrics.ui.RubricsScreen
import com.hsact.feature_bookpage.ui.BookPageScreen
import com.hsact.feature_bookpage.viewmodel.BookPageViewModel
import com.hsact.feature_catalog.ui.CatalogScreen
import com.hsact.feature_catalog.viewmodel.CatalogViewModel

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
        composable(AppDestination.Home.route) { backStackEntry ->
            val viewModel: CatalogViewModel = hiltViewModel(backStackEntry)
            CatalogScreen(
                rubricsId = emptyList(),
                rubricName = null,
                onItemClick = { bookId ->
                    navController.navigate(AppDestination.BookPage.createRoute(bookId))
                },
                viewModel = viewModel
            )
        }

        // Categories (список рубрик)
        composable(AppDestination.Categories.route) {
            RubricsScreen(
                onItemClick = { rubricId, rubricName ->
                    navController.navigate(
                        AppDestination.Catalog.createRoute(
                            rubricId,
                            rubricName
                        )
                    ) {
                        // Чтобы убрать из backStack экран каталога, когда нажмётся назад
                        //popUpTo(AppDestination.Categories.route) { inclusive = true }
                    }
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
            val rubricName = rawName?.let { Uri.decode(it) }
            val viewModel: CatalogViewModel = hiltViewModel(backStackEntry)

            CatalogScreen(
                rubricsId = listOfNotNull(rubricId),
                rubricName = rubricName,
                onItemClick = { bookId ->
                    navController.navigate(AppDestination.BookPage.createRoute(bookId))
                },
                viewModel = viewModel
            )
        }

        // Favorites
        composable(AppDestination.Favorites.route) { backStackEntry ->
            val viewModel: CatalogViewModel = hiltViewModel(backStackEntry)
            CatalogScreen(
                rubricsId = emptyList(),
                rubricName = null,
                onItemClick = { bookId ->
                    navController.navigate(AppDestination.BookPage.createRoute(bookId))
                },
                viewModel = viewModel
            )
        }

        // Account
        composable(AppDestination.Account.route) {
            ProfileScreen()
        }

        // Book page
        composable(
            route = AppDestination.BookPage.route,
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            // создаём viewModel с backStackEntry — чтобы Hilt передал SavedStateHandle с bookId
            val viewModel: BookPageViewModel = hiltViewModel(backStackEntry)

            BookPageScreen(
                viewModel = viewModel,
                onRubricClick = { rubricId, rubricName ->
                    navController.navigate(AppDestination.Catalog.createRoute(rubricId, rubricName))
                },
                onSimilarBookClick = { otherBookId ->
                    navController.navigate(AppDestination.BookPage.createRoute(otherBookId))
                }
            )
        }
    }
}