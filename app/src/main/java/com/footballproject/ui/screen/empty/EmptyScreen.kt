package com.footballproject.ui.screen.empty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.footballproject.R
import com.footballproject.ui.theme.custom.CustomTheme

@Composable
fun EmptyScreen(
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground),
    ) {
        Text(
            stringResource(R.string.empty),
            color = CustomTheme.colors.tintColor,
            style = CustomTheme.typography.heading
        )
    }
}
