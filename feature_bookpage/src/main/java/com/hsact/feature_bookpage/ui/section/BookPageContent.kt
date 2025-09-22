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
internal fun BookPageContent(state: BookPageUiState.Success) {
    Column(
        Modifier
            .fillMaxSize()
            .screenPadding()
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
        BookInfoSection(book)

        //Оглавление
        //Text(book.tableOfContents?.fromHtmlToAnnotatedString() ?: AnnotatedString(""))
        //Spacer(Modifier.height(16.dp))

        //Мета
        BookMetaSection(book)
        Spacer(Modifier.height(16.dp))

        BookActionSection()
        Spacer(Modifier.height(24.dp))
        BookCommentsSection(state.comments)
    }
}