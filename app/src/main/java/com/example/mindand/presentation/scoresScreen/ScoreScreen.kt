package com.example.mindand.presentation.scoresScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mindand.AppViewModelProvider
import com.example.mindand.Screen
import com.example.mindand.data.entity.PlayerWithScore


@Composable
fun ScoreScreen(
    navController: NavHostController,
    viewModel: ScoreViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    // Ensure data is loaded first (this can be handled in init block or LaunchedEffect)
    LaunchedEffect(Unit) {
        // You can call the suspend function to get the data (if not already done in the ViewModel)
        viewModel.getAllScoresSorted()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button to go back
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { navController.popBackStack(route = Screen.StartScreen.path, inclusive = false) }) {
                Text("Powrót")
            }
        }

        // Display the list of players with scores
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.playersWithScores) { playerWithScore ->
                PlayerScoreItem(playerWithScore)
            }
        }
    }
}

@Composable
fun PlayerScoreItem(playerWithScore: PlayerWithScore) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Player Name: ${playerWithScore.name}", fontWeight = FontWeight.Bold)
        Text(text = "Email: ${playerWithScore.email}")
        Text(text = "Score: ${playerWithScore.score}", color = MaterialTheme.colorScheme.primary)
    }
}