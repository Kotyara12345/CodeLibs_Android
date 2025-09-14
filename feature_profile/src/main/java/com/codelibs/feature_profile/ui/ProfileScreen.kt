package com.codelibs.feature_profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import com.codelibs.feature_profile.ui.components.ProfileItem

@Composable
fun ProfileScreen() {
    Column {
        ProfileItem(
            text = "Профиль",
            startIcon = Icons.Default.Person,
            onClick = { /* обработка клика */ }
        )
        ProfileItem(
            text = "Добавить книгу",
            startIcon = Icons.Default.Add,
            onClick = { /* обработка клика */ }
        )
        ProfileItem(
            text = "Мои отзывы",
            startIcon = Icons.Default.ThumbUp,
            onClick = { /* обработка клика */ }
        )
        ProfileItem(
            text = "Управление рассылками",
            startIcon = Icons.Default.Email,
            onClick = { /* обработка клика */ }
        )
        ProfileItem(
            text = "Выход",
            startIcon = null,
            showEndIcon = false,
            onClick = { /* обработка клика */ }
        )
    }
}