package com.hsact.feature_bookpage.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BookPageScreen(
    bookId: String
) {
    Text("Книга: $bookId")
}