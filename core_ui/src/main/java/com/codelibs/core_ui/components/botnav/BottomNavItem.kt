package com.codelibs.core_ui.components.botnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home", "Главная", Icons.Default.Home)
    object Categories : BottomNavItem("categories", "Категории", Icons.AutoMirrored.Filled.List)
    object Favorites : BottomNavItem("favorites", "Избранное", Icons.Default.Favorite)
    object Account : BottomNavItem("account", "Аккаунт", Icons.Default.Person)
}
