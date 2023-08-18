package com.footballproject.ui.theme.custom

import androidx.compose.ui.graphics.Color

internal val baseLightPalette = CustomColors(
    primaryBackground = Color(0xFFFFFFFF),
    primaryText = Color(0xFF3D454C),
    secondaryBackground = Color(0xFFF3F4F5),
    secondaryText = Color(0xCC7A8A99),
    tintColor = Color.Magenta,
    controlColor = Color(0xFF7A8A99),
    errorColor = Color(0xFFFF0000)
)

internal val baseDarkPalette = CustomColors(
    primaryBackground = Color(0xFF23282D),
    primaryText = Color(0xFFF2F4F5),
    secondaryBackground = Color(0xFF191E23),
    secondaryText = Color(0xCC7A8A99),
    tintColor = Color.Magenta,
    controlColor = Color(0xFF7A8A99),
    errorColor = Color(0xFFFF0000)
)

val purpleLightPalette = baseLightPalette.copy(
    tintColor = Color(0xFFAA03FF)
)

val purpleDarkPalette = baseDarkPalette.copy(
    tintColor = Color(0xFF7101A8)
)

val orangeLightPalette = baseLightPalette.copy(
    tintColor = Color(0xFFFF9D6B)
)

val orangeDarkPalette = baseDarkPalette.copy(
    tintColor = Color(0xFF9E4200)
)

val blueLightPalette = baseLightPalette.copy(
    tintColor = Color(0xFF4D88FF)
)

val blueDarkPalette = baseDarkPalette.copy(
    tintColor = Color(0xFF99BBFF)
)

val redLightPalette = baseLightPalette.copy(
    tintColor = Color(0xFFFF0D35)
)

val redDarkPalette = baseDarkPalette.copy(
    tintColor = Color(0xFF6B0919)
)

val greenLightPalette = baseLightPalette.copy(
    tintColor = Color(0xFF12B37D)
)

val greenDarkPalette = baseDarkPalette.copy(
    tintColor = Color(0xFF7EE6C3)
)
