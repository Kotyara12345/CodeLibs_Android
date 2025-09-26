package com.codelibs.feature_profile.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codelibs.core_ui.R
import com.codelibs.core_ui.components.screenPadding

@Composable
fun ProfileItem(
    text: String,
    modifier: Modifier = Modifier,
    startIcon: Painter? = null,
    showEndIcon: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp)
            .screenPadding(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row {
            if (startIcon != null) {
                Icon(
                    painter = startIcon,
                    contentDescription = "Поиск"
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            Text(text)
        }

        if (showEndIcon) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null
            )
        }
    }
}