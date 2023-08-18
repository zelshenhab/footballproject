package com.footballproject.ui.screen.settings

import androidx.compose.runtime.staticCompositionLocalOf
import com.footballproject.ui.theme.custom.CustomCorners
import com.footballproject.ui.theme.custom.CustomSize
import com.footballproject.ui.theme.custom.CustomStyle
import com.footballproject.ui.theme.custom.FontSize

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsEventBus {

    private val _currentSettings: MutableStateFlow<CurrentSettings> = MutableStateFlow(
        CurrentSettings(
            isDarkMode = true,
            cornerStyle = CustomCorners.Rounded,
            style = CustomStyle.Orange,
            textSize = CustomSize.Medium,
            paddingSize = CustomSize.Medium,
            fontSize = FontSize.Small
        )
    )
    val currentSettings: StateFlow<CurrentSettings> = _currentSettings

    fun updateDarkMode(isDarkMode: Boolean) {
        _currentSettings.value = _currentSettings.value.copy(isDarkMode = isDarkMode)
    }

    fun updateStyle(style: CustomStyle) {
        _currentSettings.value = _currentSettings.value.copy(style = style)
    }

    fun updateFontSize(fontSize: FontSize) {
        _currentSettings.value = _currentSettings.value.copy(fontSize = fontSize)
    }
}

val LocalSettingsEventBus = staticCompositionLocalOf {
    SettingsEventBus()
}
