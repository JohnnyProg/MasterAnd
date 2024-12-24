package com.example.mindand.presentation.gameScreen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularButton(
    onClick: () -> Unit,
    color: Color
) {

    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(durationMillis = 500)
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .size(50.dp)
            .background(color = MaterialTheme.colorScheme.background, shape = CircleShape),
        colors = ButtonDefaults.buttonColors(containerColor = animatedColor),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline)
    ) {}
}