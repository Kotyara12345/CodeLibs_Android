package com.hsact.feature_bookpage.ui.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codelibs.core_ui.utils.fromHtmlToAnnotatedString
import com.codelibs.core_ui.utils.toReadableDate
import com.hsact.feature_bookpage.ui.components.CommentItem
import com.hsact.feature_bookpage.ui.state.CommentsUiState

@Composable
internal fun BookCommentsSection(state: CommentsUiState, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Комментарии",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(8.dp))
        when (val state = state) {
            is CommentsUiState.Loading -> {
                CircularProgressIndicator()
            }

            is CommentsUiState.Error -> {
                Text("Ошибка: ${state.message}")
            }

            is CommentsUiState.Success -> {
                if (state.comments.isEmpty()) {
                    Text("Комментариев пока нет")
                } else {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        state.comments.forEach { comment ->
                            CommentItem(
                                username = comment.user.username,
                                content = comment.content.fromHtmlToAnnotatedString()
                                    .toString(),
                                date = comment.createdAt!!.toReadableDate()
                            )
                        }
                    }
                }
            }
        }
    }
}