package com.codelibs.core_ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.screenPadding(): Modifier =
    this.padding(horizontalScreenPadding)

val horizontalScreenPadding =  PaddingValues(horizontal = 16.dp)