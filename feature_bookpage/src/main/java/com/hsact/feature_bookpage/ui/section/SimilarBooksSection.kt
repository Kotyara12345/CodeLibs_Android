package com.hsact.feature_bookpage.ui.section

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hsact.feature_bookpage.ui.components.SimilarBookItem
import com.hsact.feature_bookpage.ui.state.SimilarBooksUiState

@Composable
internal fun SimilarBooksSection(
    state: SimilarBooksUiState,
) {
    Text(
        text = "Похожие книги",
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(Modifier.height(8.dp))
    when (state) {
        is SimilarBooksUiState.Loading -> {
            CircularProgressIndicator()
        }

        is SimilarBooksUiState.Error -> {
            Text("Ошибка: ${state.message}")
        }

        is SimilarBooksUiState.Success -> {
            if (state.books.isEmpty()) {
                Text("Похожих книг нет")
            } else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        items = state.books,
                        key = { book -> book.id }
                    ) { rubric ->
                        SimilarBookItem(
                            book = rubric,
                            onItemClick = {}
                        )
                        Spacer(Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}