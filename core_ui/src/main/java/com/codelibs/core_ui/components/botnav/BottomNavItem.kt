package com.codelibs.core_ui.components.botnav

import com.codelibs.core_ui.R

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val iconRes: Int // <- храним ID ресурса
) {
    object Home : BottomNavItem("home", "Главная", R.drawable.home)
    object Categories : BottomNavItem("categories", "Категории", R.drawable.book)
    object Favorites : BottomNavItem("favorites", "Избранное", R.drawable.heart)
    object Account : BottomNavItem("account", "Аккаунт", R.drawable.person)
}