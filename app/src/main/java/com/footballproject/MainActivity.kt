package com.footballproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.footballproject.ui.screen.settings.LocalSettingsEventBus
import com.footballproject.ui.screen.settings.SettingsEventBus
import com.footballproject.ui.theme.custom.CustomTheme
import com.footballproject.utils.CustomNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val settingsEventBus = remember { SettingsEventBus() }

            val currentSettings = settingsEventBus.currentSettings.collectAsState().value

            CustomTheme(
                style = currentSettings.style,
                darkTheme = currentSettings.isDarkMode,
                corners = currentSettings.cornerStyle,
                paddingSize = currentSettings.paddingSize,
                fontSize = currentSettings.fontSize
            ) {
                CompositionLocalProvider(
                    LocalSettingsEventBus provides settingsEventBus
                ) {
                    CustomNavHost()
                }
            }
        }
    }
}

