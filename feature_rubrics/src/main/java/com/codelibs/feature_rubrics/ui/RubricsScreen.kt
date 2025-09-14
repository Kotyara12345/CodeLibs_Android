package com.codelibs.feature_rubrics.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.codelibs.feature_rubrics.ui.components.RubricItem
import com.codelibs.feature_rubrics.ui.state.RubricsUiState
import com.codelibs.feature_rubrics.viewmodel.RubricsViewModel

@Composable
fun RubricsScreen(
    onItemClick: (Int) -> Unit,
    viewModel: RubricsViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState().value

    when (state) {
        is RubricsUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is RubricsUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Ошибка загрузки")
            }
        }

        is RubricsUiState.Success -> {
            LazyColumn {
                items(state.rubrics) { rubric ->
                    RubricItem(rubric = rubric, onItemClick = onItemClick)
                }
            }
        }
    }
}