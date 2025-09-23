package com.hsact.feature_bookpage.ui.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.codelibs.core_ui.components.screenPadding
import com.hsact.feature_bookpage.ui.state.BookPageUiState

@Composable
internal fun BookPageContent(
    state: BookPageUiState.Success,
    onRubricClick: (Int, String) -> Unit,
    onDownloadClick: () -> Unit,
    onBuyClick: () -> Unit,
    onSimilarBookClick: (Int) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Изображение книги
        val book = state.book
        book.image?.let { url ->
            AsyncImage(
                model = Builder(LocalContext.current)
                    .data(url)
                    .crossfade(true)
                    .build(),
                contentDescription = book.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Spacer(Modifier.height(16.dp))
        }
        BookInfoSection(
            book,
            Modifier
                .fillMaxWidth()
                .screenPadding(),
            onRubricClick
        )

        //Оглавление
        //Text(book.tableOfContents?.fromHtmlToAnnotatedString() ?: AnnotatedString(""))
        //Spacer(Modifier.height(16.dp))

        //Мета
        BookMetaSection(
            book, Modifier
                .fillMaxWidth()
                .screenPadding()
        )
        Spacer(Modifier.height(16.dp))

        BookActionSection(
            Modifier
                .fillMaxWidth()
                .screenPadding(),
            onBuyClick,
            onDownloadClick
        )
        Spacer(Modifier.height(24.dp))

        SimilarBooksSection(
            state.similarBooks,
            Modifier
                .fillMaxWidth()
                .screenPadding(),
            onItemClick = onSimilarBookClick
        )
        Spacer(Modifier.height(24.dp))

        BookCommentsSection(
            state.comments, Modifier
                .fillMaxWidth()
                .screenPadding()
        )
    }
}