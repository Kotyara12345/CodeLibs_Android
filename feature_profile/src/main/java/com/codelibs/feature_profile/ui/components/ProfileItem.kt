package com.codelibs.feature_profile.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ProfileItem(
    text: String,
    modifier: Modifier = Modifier,
    startIcon: ImageVector? = null,      // опциональная иконка слева
    showEndIcon: Boolean = true,         // показывать ">"
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row {
            if (startIcon != null) {
                Icon(
                    imageVector = startIcon,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            Text(text)
        }

        if (showEndIcon) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null
            )
        }
    }
}