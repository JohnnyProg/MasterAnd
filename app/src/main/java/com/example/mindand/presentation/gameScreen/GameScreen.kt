package com.example.mindand.presentation.gameScreen

import android.util.Log
import androidx.compose.animation.animateContentSize

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mindand.AppViewModelProvider
import com.example.mindand.presentation.gameScreen.components.GameRow

import com.example.mindand.Screen
import com.example.mindand.presentation.mainScreen.StartViewModel
import kotlinx.coroutines.launch

@Composable
fun GameScreen(navController: NavHostController,
               numberOfColors: Long,
               viewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory),
               playerId: Long
) {
    val coroutineScope = rememberCoroutineScope()
    // Ensure data is loaded first (this can be handled in init block or LaunchedEffect)
    LaunchedEffect(Unit) {
        // You can call the suspend function to get the data (if not already done in the ViewModel)
        coroutineScope.launch {
            viewModel.getUserData(playerId)
        }
        viewModel.getColorBase(numberOfColors)
    }
    LaunchedEffect(viewModel.isWin.value) {
        if (viewModel.isWin.value) {
            Log.d("GAME_SCREEN", "Saving player score to the database; playerId = ${playerId}")
            viewModel.saveScore(playerId)
        }
    }


    Text(viewModel.user.value.name + " " + viewModel.user.value.email + " " + viewModel.user.value.playerId )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {


            Text(
                "MasterAnd Game", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Text(
                "Your score: ${viewModel.attempts.size}", style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            )
//
            // viewModel.attempts List
            LazyColumn(
                modifier = Modifier.animateContentSize(),
//                reverseLayout = true
            ) {
                items(viewModel.attempts.size) { index ->
                    GameRow(
                        selectedColors = viewModel.attempts[index],
                        feedback = viewModel.feedbackList[index],
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
//            colors = viewModel.selectedColors.toList(),
//            onClick = { index -> selectNextAvailableColor(index) }
//        )
            if (!viewModel.isWin.value) {
                GameRow(
                    selectedColors = viewModel.selectedColors.toList(),
                    feedback = listOf(Color.Gray, Color.Gray, Color.Gray, Color.Gray),
                    isClickable = viewModel.isClickable.value,
                    onSelectColorClick = { index -> viewModel.selectNextAvailableColor(index) },
                    onCheckClick = { viewModel.handleCheckClick() }
                )
            }


            Button(
                onClick = {
                    navController.navigate(Screen.ProfileScreen.path)
                },
                enabled = viewModel.isWin.value
            ) {
                Text("High score table")
            }
        }
        Button(
            onClick = {
                navController.navigate(Screen.StartScreen.path)
            },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text("LogOut")
        }
    }

}





