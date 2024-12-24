package com.example.mindand.presentation.gameScreen.utils

import androidx.compose.ui.graphics.Color

fun selectRandomColors(availableColors: List<Color>): List<Color> {
    return availableColors.shuffled().take(4)
}

fun checkColors(selectedColors: List<Color>, secretColors: List<Color>): List<Color> {
    return selectedColors.mapIndexed { index, color ->
        when {
            color == secretColors[index] -> Color.Red
            color in secretColors -> Color.Yellow
            else -> Color.Gray
        }
    }
}