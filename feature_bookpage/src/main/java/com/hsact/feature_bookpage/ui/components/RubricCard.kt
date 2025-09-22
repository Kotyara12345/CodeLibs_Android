package com.hsact.feature_bookpage.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codelibs.core_domain.model.Rubric

@Composable
internal fun RubricItem(
    rubric: Rubric,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onItemClick(rubric.id) }
    ) {
        Text(rubric.name, modifier = Modifier.padding(8.dp))
    }
}