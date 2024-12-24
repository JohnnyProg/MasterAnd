package com.example.mindand.presentation.gameScreen

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mindand.presentation.gameScreen.components.GameRow
import com.example.mindand.presentation.gameScreen.components.SelectableColorsRow
import com.example.mindand.presentation.gameScreen.utils.RowData


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameScreen(
    availableColors: List<Color>,
    secretColors: List<Color>,
    onGameEnd: (Boolean) -> Unit
) {
    val selectedColors = remember { mutableStateListOf<Color>(Color.Gray, Color.Gray, Color.Gray, Color.Gray) }
    val attempts = remember { mutableStateListOf<List<Color>>() }
    val feedbackList = remember { mutableStateListOf<List<Color>>() }
    val isClickable = remember { mutableStateOf(false) }

    fun selectNextAvailableColor(index: Int) {
        val selectedColor = selectedColors[index]
        selectedColors[index] = Color.Gray
        val available = availableColors - selectedColors
        val selectedIndex = available.indexOf(selectedColor)
        selectedColors[index] = available[((selectedIndex + 1) % (available.size))]
        if (!selectedColors.contains(Color.Gray)) {
            isClickable.value = true
        }
    }

    fun checkColors(): List<Color> {
        Log.d("Secret colors", secretColors.toString())
        return selectedColors.mapIndexed { index, color ->
            when {
                color == secretColors[index] -> Color.Green
                color in secretColors -> Color.Yellow
                else -> Color.Gray
            }
        }
    }

    fun handleCheckClick() {
        val feedback = checkColors()
        attempts.add(selectedColors.toList())
        feedbackList.add(feedback.toList())
        Log.d("attempts", attempts.toString())
        Log.d("feedbackList", feedbackList.toString())
        selectedColors[0] = Color.Gray
        selectedColors[1] = Color.Gray
        selectedColors[2] = Color.Gray
        selectedColors[3] = Color.Gray
        isClickable.value = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("MasterAnd Game", style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary
        ))
//
        // Attempts List
        LazyColumn(
            modifier = Modifier.animateContentSize(),
            reverseLayout = true
            ) {
            items(attempts.size) { index ->
                GameRow(
                    selectedColors = attempts[index],
                    feedback = feedbackList[index],
                    isClickable = false,
                    onSelectColorClick = { },
                    onCheckClick = { },
//                    modifier = Modifier
//                        .animateItem(
//                            fadeInSpec = spring(stiffness = Spring.StiffnessMediumLow),
//                            placementSpec = spring(
//                                stiffness = Spring.StiffnessMediumLow,
//                                visibilityThreshold = IntOffset.VisibilityThreshold
//                            ),
//                            fadeOutSpec = spring(stiffness = Spring.StiffnessMediumLow)
//                        )
                )
            }
//                    modifier = Modifier.animateItem(
//                        fadeInSpec = spring(stiffness = Spring.StiffnessMediumLow),
//                        placementSpec = spring(
//                            stiffness = Spring.StiffnessMediumLow,
//                            visibilityThreshold = IntOffset.VisibilityThreshold
//                        ),
//                        fadeOutSpec = spring(stiffness = Spring.StiffnessMediumLow)
//                    )

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Controls for the current guess
//        SelectableColorsRow(
//            colors = selectedColors.toList(),
//            onClick = { index -> selectNextAvailableColor(index) }
//        )
        GameRow(
            selectedColors = selectedColors.toList(),
            feedback = listOf(Color.Gray, Color.Gray, Color.Gray, Color.Gray),
            isClickable = isClickable.value,
            onSelectColorClick = {index -> selectNextAvailableColor(index) },
            onCheckClick = {handleCheckClick() }
        )

//        Button(
//            onClick = { handleCheckClick() },
//            enabled = true
//        ) {
//            Text("Check Attempt")
//        }
    }
}





