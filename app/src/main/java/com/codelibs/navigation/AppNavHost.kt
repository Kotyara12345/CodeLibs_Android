package com.codelibs.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
//    NavHost(
//        navController = navController,
//        startDestination = "catalog"
//    ) {
//        composable("catalog") { backStackEntry ->
//            CatalogScreen(
//                onItemClick = { itemId ->
//                    navController.navigate("details/$itemId")
//                }
//            )
//        }
//
//        composable("details/{itemId}") { backStackEntry ->
//            val itemId = backStackEntry.arguments?.getString("itemId")
//            BookPageScreen(itemId!!)
//        }

}