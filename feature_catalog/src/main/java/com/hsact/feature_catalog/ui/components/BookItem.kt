package com.hsact.feature_catalog.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.codelibs.core_domain.model.Book
import com.codelibs.core_ui.utils.fromHtmlToAnnotatedString
import com.codelibs.core_ui.utils.toAuthorString

@Composable
fun BookItem(
    book: Book,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onItemClick(book.id) },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            // Левая часть: картинка с фиксированной высотой
            Image(
                painter = rememberAsyncImagePainter(book.image),
                contentDescription = book.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(0.4f)
                    .height(200.dp) // фиксируем маленькую высоту для списка
                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
            )

            // Правая часть: текстовая информация
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(12.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = book.title ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${book.authors.toAuthorString()} · ${book.yearRelease ?: ""}",
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "⭐ ${book.rating}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = book.content!!.fromHtmlToAnnotatedString(),
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3
                    )
                }
            }
        }
    }
}