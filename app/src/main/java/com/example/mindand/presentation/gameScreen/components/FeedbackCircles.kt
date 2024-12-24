package com.example.mindand.presentation.gameScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FeedbackCircles(feedback: List<Color>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SmallCircle(feedback[0])
            SmallCircle(feedback[1])
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SmallCircle(feedback[2])
            SmallCircle(feedback[3])
        }
    }
}
