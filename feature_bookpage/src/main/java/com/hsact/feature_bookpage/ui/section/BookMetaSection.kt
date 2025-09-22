package com.hsact.feature_bookpage.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codelibs.core_domain.model.Book
import com.codelibs.core_ui.utils.toReadableFileSize
import com.hsact.feature_bookpage.ui.components.MetaRow
import java.util.Locale.getDefault

@Composable
internal fun BookMetaSection(book: Book, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MetaRow("Год издания", book.yearRelease.toString())
        MetaRow("Страниц", book.pagesNumber?.toString() ?: "-")
        MetaRow("Тип файла", book.fileFormat ?: "-")
        MetaRow("Размер файла", book.fileSize.toReadableFileSize(getDefault()))
        MetaRow("Издательство", book.publisher.name)
        if (!book.translator.isNullOrEmpty()) {
            MetaRow("Переводчик", book.translator ?: "-")
        }
        MetaRow("Добавлено", book.accountMini.username)
    }
}