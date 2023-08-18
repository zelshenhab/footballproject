package com.footballproject.ui.screen.settings

import com.footballproject.ui.theme.custom.CustomCorners
import com.footballproject.ui.theme.custom.CustomSize
import com.footballproject.ui.theme.custom.CustomStyle
import com.footballproject.ui.theme.custom.FontSize

data class CurrentSettings(
    val isDarkMode: Boolean,
    val textSize: CustomSize,
    val paddingSize: CustomSize,
    val cornerStyle: CustomCorners,
    val style: CustomStyle,
    val fontSize: FontSize,
)
