package com.footballproject.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.footballproject.R

sealed class Screen(
    val route: String,
    @StringRes
    val name: Int? = null,
    val icon: ImageVector? = null,
) {
    object Settings : Screen(
        route = "settings",
        name = R.string.settings,
        icon = Icons.Filled.Settings,
    )

    object List : Screen(
        route = "list",
        name = R.string.list,
        icon = Icons.Filled.List
    )

    object Empty : Screen(
        route = "empty",
        name = R.string.empty,
        icon = Icons.Filled.Lock
    )

    object Team : Screen("team/{teamId}")

}
