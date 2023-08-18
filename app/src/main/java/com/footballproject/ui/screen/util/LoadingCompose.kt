package com.footballproject.ui.screen.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.footballproject.ui.theme.custom.CustomTheme


@Composable
fun LoadingCompose(
    isShow: Boolean,
) {
    if (isShow)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CircularProgressIndicator(
                color = CustomTheme.colors.errorColor,
            )
        }
}
