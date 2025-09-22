package com.hsact.feature_bookpage.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.codelibs.core_domain.model.Book
import com.codelibs.core_ui.utils.toAuthorString

@Composable
internal fun SimilarBookItem(
    book: Book,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(140.dp) // можно подогнать под дизайн
            .clickable { onItemClick(book.id) }
            .padding(4.dp),
    ) {
        // Обложка
        Image(
            painter = rememberAsyncImagePainter(model = book.image),
            contentDescription = book.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f) // обложка выше ширины
                .clip(RoundedCornerShape(6.dp))
        )

        Spacer(Modifier.height(6.dp))

        // Рейтинг
        Text(
            text = "⭐ ${book.rating}",
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.Gray,
                fontSize = 12.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Название
        Text(
            text = book.title.orEmpty(),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth()
        )

        // Автор (берём первого, если список не пустой)
        Text(
            text = book.authors.toAuthorString(),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Gray
        )
    }
}