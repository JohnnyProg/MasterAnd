package com.example.mindand.presentation.gameScreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp


@Composable
fun GameRow(
    selectedColors: List<Color>,
    feedback: List<Color>,
    isClickable: Boolean,
    onSelectColorClick: (Int) -> Unit,
    onCheckClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var moved by remember { mutableStateOf(false) }
    val offset by animateIntOffsetAsState(
        targetValue = if (moved) {
            IntOffset.Zero
        } else {
            IntOffset(-10000, 0)
        },
        label = "offset",
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )

    LaunchedEffect(Unit) {
        moved = true
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .offset {
                offset
            }
    ) {
        SelectableColorsRow(
            colors = selectedColors,
            onClick = onSelectColorClick
        )

        FeedbackCircles(feedback = feedback)

        AnimatedVisibility(visible = isClickable) {
            IconButton(
                onClick = onCheckClick,
                enabled = isClickable
            ) {
                Icon(Icons.Filled.Check, contentDescription = "Check Attempt")
            }
        }
    }
}
