package com.codelibs.feature_rubrics.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.codelibs.feature_rubrics.ui.components.RubricItem
import com.codelibs.feature_rubrics.ui.state.RubricsUiState
import com.codelibs.feature_rubrics.viewmodel.RubricsViewModel

@Composable
fun RubricsScreen(
    onItemClick: (Int, String) -> Unit,
    viewModel: RubricsViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value
    val query by viewModel.query.collectAsState()

    Column(Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query,
            onValueChange = { viewModel.onQueryChange(it) },
            label = { Text("Поиск категории") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true
        )

        when (state) {
            is RubricsUiState.Loading -> { /* ... */ }
            is RubricsUiState.Error -> { /* ... */ }
            is RubricsUiState.Success -> {
                LazyColumn {
                    items(state.rubrics) { rubric ->
                        // RubricItem остаётся с onItemClick: (Int) -> Unit
                        RubricItem(rubric = rubric, onItemClick = { id ->
                            onItemClick(id, rubric.name) // пробрасываем имя
                        })
                    }
                }
            }
        }
    }
}