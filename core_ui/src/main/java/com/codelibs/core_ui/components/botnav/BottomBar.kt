package com.codelibs.core_ui.components.botnav

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Categories,
        BottomNavItem.Favorites,
        BottomNavItem.Account
    )

    NavigationBar {
        val currentDestination = navController
            .currentBackStackEntryAsState().value?.destination

        items.forEach { item ->
            val selected = currentDestination?.route == item.route

            // Анимируем цвет иконки и текста
            val iconColor by animateColorAsState(
                targetValue = if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurfaceVariant,
                label = "IconColorAnim"
            )
            val textColor by animateColorAsState(
                targetValue = if (selected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurfaceVariant,
                label = "TextColorAnim"
            )

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.label,
                        tint = iconColor
                    )
                },
                label = { Text(text = item.label, color = textColor) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent, // убираем прямоугольную подсветку
                    selectedIconColor = Color.Unspecified, // отключаем авто-цвет
                    unselectedIconColor = Color.Unspecified,
                    selectedTextColor = Color.Unspecified,
                    unselectedTextColor = Color.Unspecified
                )
            )
        }
    }
}