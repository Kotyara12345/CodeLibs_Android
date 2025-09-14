package com.codelibs.feature_rubrics.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelibs.core_domain.model.book.Rubric

@Composable
fun RubricItem(
    rubric: Rubric,
    onItemClick: (Int) -> Unit
) {
    Text(
        text = rubric.name,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(rubric.id) }
            .padding(16.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Preview(showBackground = true)
@Composable
fun RubricItemPreview() {
    RubricItem(
        rubric = Rubric(
            id = 1,
            name = "Kotlin",
            slug = "kotlin",
            description = "Description of Kotlin"
        ),
        onItemClick = {}
    )
}