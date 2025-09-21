package com.hsact.feature_bookpage.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codelibs.core_ui.components.screenPadding
import com.codelibs.core_ui.utils.fromHtmlToAnnotatedString
import com.codelibs.core_ui.utils.toAuthorString
import com.codelibs.core_ui.utils.toReadableDate
import com.codelibs.core_ui.utils.toReadableFileSize
import com.hsact.feature_bookpage.ui.components.CommentItem
import com.hsact.feature_bookpage.ui.components.MetaRow
import com.hsact.feature_bookpage.ui.components.RubricItem
import com.hsact.feature_bookpage.ui.state.BookPageUiState
import com.hsact.feature_bookpage.ui.state.CommentsUiState
import com.hsact.feature_bookpage.viewmodel.BookPageViewModel
import java.util.Locale

@Composable
fun BookPageScreen(
    viewModel: BookPageViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    when (state) {
        is BookPageUiState.Loading -> CircularProgressIndicator()
        is BookPageUiState.Error -> Text("Error: ${state.message}")
        is BookPageUiState.Success -> {
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
                        model = ImageRequest.Builder(LocalContext.current)
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
                // Заголовок книги
                Text(book.title.toString(), style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))

                // Автор
                Text(book.authors.toAuthorString())
                Spacer(Modifier.height(16.dp))

                //Рубрики
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        items = book.rubrics,
                        key = { rubric -> rubric.id } // если у рубрики есть id
                    ) { rubric ->
                        RubricItem(
                            rubric = rubric,
                            onItemClick = {}
                        )
                        Spacer(Modifier.width(8.dp))
                    }
                }
                Spacer(Modifier.height(16.dp))

                //Оценки
                Text(
                    text = "⭐ ${book.rating}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(16.dp))

                //Описание книги
                Text(book.content?.fromHtmlToAnnotatedString() ?: AnnotatedString(""))
                Spacer(Modifier.height(16.dp))

                //Оглавление
//                Text(book.tableOfContents?.fromHtmlToAnnotatedString() ?: AnnotatedString(""))
//                Spacer(Modifier.height(16.dp))

                //Мета
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MetaRow("Год издания", book.yearRelease.toString())
                    MetaRow("Страниц", book.pagesNumber?.toString() ?: "-")
                    MetaRow("Тип файла", book.fileFormat ?: "-")
                    MetaRow("Размер файла", book.fileSize.toReadableFileSize(Locale.getDefault()))
                    MetaRow("Издательство", book.publisher.name)
                    if (!book.translator.isNullOrEmpty()) {
                        MetaRow("Переводчик", book.translator ?: "-")
                    }
                    MetaRow("Добавлено", book.accountMini.username)
                }
                Spacer(Modifier.height(16.dp))

                //Кнопка "Купить"
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Купить")
                }
                Spacer(Modifier.height(2.dp))
                Text(
                    text = "Поддержите автора, купив эту книгу",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(Modifier.height(16.dp))

                //Кнопка "Скачать"
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Скачать")
                }
                Spacer(Modifier.height(24.dp))
                Text(
                    text = "Комментарии",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(8.dp))

                when (val commentsState = state.comments) {
                    is CommentsUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is CommentsUiState.Error -> {
                        Text("Ошибка: ${commentsState.message}")
                    }

                    is CommentsUiState.Success -> {
                        if (commentsState.comments.isEmpty()) {
                            Text("Комментариев пока нет")
                        } else {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                commentsState.comments.forEach { comment ->
                                    CommentItem(
                                        username = comment.user.username,
                                        content = comment.content.fromHtmlToAnnotatedString().toString(),
                                        date = comment.createdAt!!.toReadableDate()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}