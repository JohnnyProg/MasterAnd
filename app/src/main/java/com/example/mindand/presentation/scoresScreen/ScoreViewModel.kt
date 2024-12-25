package com.example.mindand.presentation.scoresScreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.mindand.data.entity.PlayerWithScore
import com.example.mindand.data.entity.Score
import com.example.mindand.data.repository.ScoreRepository

class ScoreViewModel(private val scoreRepository: ScoreRepository): ViewModel() {

    val scores =  mutableStateListOf<Score>();
    val playersWithScores = mutableStateListOf<PlayerWithScore>()

    suspend fun getAllScoresSorted() {
        scores.addAll(scoreRepository.getScoresSorted())
        Log.d("ScoreViewModel", "all scores " + scores.toString())
        playersWithScores.addAll(scoreRepository.getPlayerScoreSorted())
        Log.d("ScoreViewModel", "all scores with players" + playersWithScores.toString())
    }

}