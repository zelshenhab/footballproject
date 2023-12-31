package com.footballproject.ui.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.footballproject.ui.theme.custom.CustomStyle
import com.footballproject.ui.theme.custom.CustomTheme
import com.footballproject.ui.theme.custom.FontSize
import com.footballproject.ui.theme.custom.blueDarkPalette
import com.footballproject.ui.theme.custom.blueLightPalette
import com.footballproject.ui.theme.custom.orangeDarkPalette
import com.footballproject.ui.theme.custom.orangeLightPalette
import com.footballproject.ui.theme.custom.purpleDarkPalette
import com.footballproject.ui.theme.custom.purpleLightPalette
import com.footballproject.utils.Click

@Composable
fun SettingsScreen(
) {
    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.padding(CustomTheme.shapes.padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Dark Theme",
                    color = CustomTheme.colors.primaryText,
                    style = CustomTheme.typography.body
                )
                Checkbox(
                    checked = currentSettings.isDarkMode,
                    onCheckedChange = {
                        settingsEventBus.updateDarkMode(!currentSettings.isDarkMode)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = CustomTheme.colors.tintColor,
                        uncheckedColor = CustomTheme.colors.secondaryText
                    )
                )
            }

            Divider(
                modifier = Modifier.padding(start = CustomTheme.shapes.padding),
                thickness = 0.5.dp,
                color = CustomTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = CustomTheme.colors.secondaryBackground,
                shape = CustomTheme.shapes.cornersStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Text size",
                        color = CustomTheme.colors.secondaryText,
                        style = CustomTheme.typography.body,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Card(
                            modifier = Modifier.weight(1f),
                            backgroundColor = CustomTheme.colors.primaryBackground
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp),
                                    text = "Small",
                                    color = CustomTheme.colors.primaryText,
                                    style = CustomTheme.typography.body
                                )
                                Checkbox(
                                    checked = currentSettings.fontSize == FontSize.Small,
                                    onCheckedChange = {
                                        settingsEventBus.updateFontSize(FontSize.Small)
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = CustomTheme.colors.tintColor,
                                        uncheckedColor = CustomTheme.colors.secondaryText
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Card(
                            modifier = Modifier.weight(1f),
                            backgroundColor = CustomTheme.colors.primaryBackground
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp),
                                    text = "Big",
                                    color = CustomTheme.colors.primaryText,
                                    style = CustomTheme.typography.body,

                                    )
                                Checkbox(
                                    checked = currentSettings.fontSize == FontSize.Big,
                                    onCheckedChange = {
                                        settingsEventBus.updateFontSize(FontSize.Big)
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = CustomTheme.colors.tintColor,
                                        uncheckedColor = CustomTheme.colors.secondaryText
                                    )
                                )
                            }
                        }
                    }
                }
            }

            Divider(
                modifier = Modifier.padding(start = CustomTheme.shapes.padding),
                thickness = 0.5.dp,
                color = CustomTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = CustomTheme.colors.secondaryBackground,
                shape = CustomTheme.shapes.cornersStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tint color",
                        color = CustomTheme.colors.secondaryText,
                        style = CustomTheme.typography.body,
                    )

                    Row(
                        modifier = Modifier
                            .padding(CustomTheme.shapes.padding)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ColorCard(color = if (currentSettings.isDarkMode)
                            purpleDarkPalette.tintColor else purpleLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(CustomStyle.Purple)
                            })
                        ColorCard(color = if (currentSettings.isDarkMode)
                            orangeDarkPalette.tintColor else orangeLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(CustomStyle.Orange)
                            })
                        ColorCard(color = if (currentSettings.isDarkMode)
                            blueDarkPalette.tintColor else blueLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(CustomStyle.Blue)
                            })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColorCard(color: Color, onClick: Click) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .size(56.dp, 56.dp),
        backgroundColor = color,
        shape = CustomTheme.shapes.cornersStyle,
        elevation = 3.dp,
    ) {}
}

@Preview
@Composable
fun SettingsPreview() {
    SettingsScreen()
}
