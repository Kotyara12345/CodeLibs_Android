package com.codelibs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codelibs.core_ui.components.botnav.BottomBar
import com.codelibs.core_ui.components.topbar.TopBar
import com.codelibs.core_ui.theme.CodeLibsTheme
import com.codelibs.navigation.AppDestination
import com.codelibs.navigation.AppNavHost

@Composable
fun AppScaffold() {
    CodeLibsTheme {
        val navController = rememberNavController()
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination?.route

        // нормализуем маршрут: если это любой catalog-экран — считаем его 'home'
        val selectedDestination = when {
            currentDestination?.startsWith("catalog") == true -> AppDestination.Home.route
            else -> currentDestination
        }

        val bottomBarDestinations = listOf(
            AppDestination.Home.route,
            AppDestination.Categories.route,
            AppDestination.Favorites.route,
            AppDestination.Account.route
        )
        val topBarDestinations = listOf(
            AppDestination.Home.route,
            AppDestination.Favorites.route
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                // используем selectedDestination
                if (selectedDestination in topBarDestinations) {
                    TopBar(
                        searchQuery = "",
                        onQueryChange = { /* TODO */ },
                        onSearchClick = { /* TODO */ },
                        onFilterClick = { /* TODO */ }
                    )
                }
            },
            bottomBar = {
                if (selectedDestination in bottomBarDestinations) {
                    BottomBar(
                        navController = navController
                    )
                }
            }
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}