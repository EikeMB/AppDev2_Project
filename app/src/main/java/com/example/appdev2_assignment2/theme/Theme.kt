package com.example.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColors = lightColorScheme(
    primary = lightBluePrimary,
    onPrimary = lightBlueOnPrimary,
    primaryContainer = lightBluePrimaryContainer,
    onPrimaryContainer = lightBlueOnPrimaryContainer,
    secondary = greySurface,
    onSecondary = greyOnSurface,
    secondaryContainer = greySurfaceVariant,
    onSecondaryContainer = greyOnSurfaceVariant,
    tertiary = lightBlueOnPrimary, // Using the onPrimary color for tertiary
    onTertiary = lightBluePrimary, // Using the primary color for onTertiary
    tertiaryContainer = lightBlueOnPrimaryContainer, // Using the onPrimaryContainer color for tertiaryContainer
    onTertiaryContainer = lightBluePrimaryContainer, // Using the primaryContainer color for onTertiaryContainer
    error = Color.Red, // Replace with your error color
    errorContainer = greyOnSurfaceVariant, // Using greyOnSurfaceVariant as a placeholder
    onError = Color.White, // Replace with your onError color
    onErrorContainer = greyOutline, // Using greyOutline as a placeholder
    background = lightBluePrimaryContainer, // Using the primaryContainer color for background
    onBackground = lightBlueOnPrimaryContainer, // Using the onPrimaryContainer color for onBackground
    surface = lightBluePrimaryContainer, // Using the primaryContainer color for surface
    onSurface = lightBlueOnPrimaryContainer, // Using the onPrimaryContainer color for onSurface
    surfaceVariant = greySurfaceVariant,
    onSurfaceVariant = greyOnSurfaceVariant,
    outline = greyOutline,
    inverseOnSurface = greySurface, // Using greySurface as a placeholder
    inverseSurface = greyOnSurface, // Using greyOnSurface as a placeholder
    inversePrimary = lightBluePrimary, // Using the primary color for inversePrimary
    surfaceTint = lightBluePrimary, // Using the primary color for surfaceTint
    outlineVariant = greyOutline,
    scrim = greySurface, // Using greySurface as a placeholder
)


private val DarkColors = darkColorScheme(
    primary = darkBluePrimary,
    onPrimary = darkBlueOnPrimary,
    primaryContainer = darkBluePrimaryContainer,
    onPrimaryContainer = darkBlueOnPrimaryContainer,
    secondary = greySurface,
    onSecondary = greyOnSurface,
    secondaryContainer = greySurfaceVariant,
    onSecondaryContainer = greyOnSurfaceVariant,
    tertiary = darkBlueOnPrimary, // Using the onPrimary color for tertiary
    onTertiary = darkBluePrimary, // Using the primary color for onTertiary
    tertiaryContainer = darkBlueOnPrimaryContainer, // Using the onPrimaryContainer color for tertiaryContainer
    onTertiaryContainer = darkBluePrimaryContainer, // Using the primaryContainer color for onTertiaryContainer
    error = Color.Red, // Replace with your error color
    errorContainer = greyOnSurfaceVariant, // Using greyOnSurfaceVariant as a placeholder
    onError = Color.White, // Replace with your onError color
    onErrorContainer = greyOutline, // Using greyOutline as a placeholder
    background = darkBluePrimaryContainer, // Using the primaryContainer color for background
    onBackground = darkBlueOnPrimaryContainer, // Using the onPrimaryContainer color for onBackground
    surface = darkBluePrimaryContainer, // Using the primaryContainer color for surface
    onSurface = darkBlueOnPrimaryContainer, // Using the onPrimaryContainer color for onSurface
    surfaceVariant = greySurfaceVariant,
    onSurfaceVariant = greyOnSurfaceVariant,
    outline = greyOutline,
    inverseOnSurface = greySurface, // Using greySurface as a placeholder
    inverseSurface = greyOnSurface, // Using greyOnSurface as a placeholder
    inversePrimary = darkBluePrimary, // Using the primary color for inversePrimary
    surfaceTint = darkBluePrimary, // Using the primary color for surfaceTint
    outlineVariant = greyOutline,
    scrim = greySurface, // Using greySurface as a placeholder
)

@Composable
fun AppDev2_Assignment2Theme(
  useDarkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable() () -> Unit
) {
  val colors = if (!useDarkTheme) {
    LightColors
  } else {
    DarkColors
  }

  MaterialTheme(
    colorScheme = colors,
    content = content
  )
}