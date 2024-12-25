package com.example.mindand.presentation.gameScreen.components

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FeedbackCircles(feedback: List<Color>) {
    val animatedColors = remember(feedback) { feedback.map { Animatable(Color.Gray) } }

    LaunchedEffect(Unit) {
        delay(500L) // Delay before the animation starts
        animatedColors.forEachIndexed { index, animatable ->
            launch {
                animatable.animateTo(
                    targetValue = feedback[index],
                    animationSpec = tween(durationMillis = 500)
                )
            }
            delay(200L) // Delay between animations for each circle
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SmallCircle(animatedColors[0].value)
            SmallCircle(animatedColors[1].value)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SmallCircle(animatedColors[2].value)
            SmallCircle(animatedColors[3].value)
        }
    }
}
