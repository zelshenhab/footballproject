package com.footballproject.ui.screen.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.footballproject.ui.theme.custom.CustomTheme

@Composable
fun ErrorCompose(
    message: String?,
) {
    if (message != null) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = message,
                color = CustomTheme.colors.errorColor,
                style = CustomTheme.typography.large,
            )
        }
    }
}
