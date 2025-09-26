package com.codelibs.feature_profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.codelibs.core_ui.R
import com.codelibs.core_ui.components.horizontalScreenPadding
import com.codelibs.feature_profile.ui.components.ProfileItem
import com.codelibs.feature_profile.ui.state.ProfileUiState
import com.codelibs.feature_profile.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is ProfileUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ProfileUiState.Unauthorized -> {
            LoginForm(
                onLoginClick = { username, password ->
                    viewModel.login(username, password)
                }
            )
        }

        is ProfileUiState.Authorized -> {
            val user = (state as ProfileUiState.Authorized).user
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.padding(horizontalScreenPadding),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(user.avatarUrl),
                        contentDescription = user.username,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp) // фиксированный размер аватара
                            .clip(CircleShape) // делаем круглым
                    )
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text(user.username, style = MaterialTheme.typography.titleMedium)
                        Text(user.email ?: "", style = MaterialTheme.typography.bodyMedium)
                    }
                }
                Spacer(Modifier.height(32.dp))
                ProfileItem(
                    text = "Профиль",
                    startIcon = painterResource(id = R.drawable.user),
                    onClick = { /* ... */ }
                )
                ProfileItem(
                    text = "Добавить книгу",
                    startIcon = painterResource(id = R.drawable.note_favorite),
                    onClick = { /* ... */ }
                )
                ProfileItem(
                    text = "Мои отзывы",
                    startIcon = painterResource(id = R.drawable.message_edit),
                    onClick = { /* ... */ }
                )
                ProfileItem(
                    text = "Управление рассылками",
                    startIcon = painterResource(id = R.drawable.sms_edit),
                    onClick = { /* ... */ }
                )
                ProfileItem(
                    text = "Выход",
                    startIcon = null,
                    showEndIcon = false,
                    onClick = { viewModel.logout() }
                )
                Text(user.toString())
            }
        }

        is ProfileUiState.Error -> {
            val message = (state as ProfileUiState.Error).message
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text("Ошибка: $message", color = MaterialTheme.colorScheme.error)
                Button(onClick = { viewModel.logout() }) {
                    Text("Попробовать снова")
                }
            }
        }
    }
}

@Composable
fun LoginForm(onLoginClick: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { onLoginClick(username, password) },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Login")
        }
    }
}