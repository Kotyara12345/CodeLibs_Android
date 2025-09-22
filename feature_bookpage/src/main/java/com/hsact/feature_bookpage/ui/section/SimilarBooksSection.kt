package com.hsact.feature_bookpage.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codelibs.core_ui.components.horizontalScreenPadding
import com.hsact.feature_bookpage.ui.components.SimilarBookItem
import com.hsact.feature_bookpage.ui.state.SimilarBooksUiState

@Composable
internal fun SimilarBooksSection(
    state: SimilarBooksUiState,
    modifier: Modifier
) {
    Text(
        text = "Похожие книги",
        modifier = modifier,
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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = horizontalScreenPadding
                ) {
                    items(
                        items = state.books,
                        key = { book -> book.id }
                    ) { book ->
                        SimilarBookItem(
                            book = book,
                            onItemClick = {}
                        )
                    }
                }
            }
        }
    }
}