package com.hsact.feature_bookpage.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.codelibs.core_domain.model.Book
import com.codelibs.core_ui.components.horizontalScreenPadding
import com.codelibs.core_ui.utils.fromHtmlToAnnotatedString
import com.codelibs.core_ui.utils.toAuthorString
import com.hsact.feature_bookpage.ui.components.RubricItem

@Composable
internal fun BookInfoSection(
    book: Book,
    modifier: Modifier,
    onRubricClick: (Int, String) -> Unit) {
    Column {
        // Заголовок книги
        Text(
            book.title.toString(),
            modifier = modifier,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))
        // Автор
        Text(book.authors.toAuthorString(), modifier = modifier)
        Spacer(Modifier.height(16.dp))
        //Рубрики
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = horizontalScreenPadding
        ) {
            items(
                items = book.rubrics,
                key = { rubric -> rubric.id } // если у рубрики есть id
            ) { rubric ->
                RubricItem(
                    rubric = rubric,
                    onItemClick = onRubricClick
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        //Оценки
        Text(
            text = "⭐ ${book.rating}",
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(16.dp))
        //Описание книги
        Text(book.content?.fromHtmlToAnnotatedString() ?: AnnotatedString(""), modifier = modifier)
    }
}