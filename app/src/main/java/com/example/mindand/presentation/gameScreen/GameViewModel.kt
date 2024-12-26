package com.example.mindand.presentation.gameScreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.mindand.data.entity.Player
import com.example.mindand.data.entity.Score
import com.example.mindand.data.repository.PlayersRepository
import com.example.mindand.data.repository.ScoreRepository
import com.example.mindand.presentation.gameScreen.utils.selectRandomColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val scoreRepository: ScoreRepository, private val playersRepository: PlayersRepository): ViewModel() {

    val selectedColors = mutableStateListOf<Color>(Color.Gray, Color.Gray, Color.Gray, Color.Gray)
    val attempts = mutableStateListOf<List<Color>>()
    val feedbackList = mutableStateListOf<List<Color>>()
    val isClickable = mutableStateOf(false)
    val isWin = mutableStateOf(false)
    val availableColors = mutableStateListOf<Color>()
    val secretColors = mutableStateListOf<Color>()
    val user = mutableStateOf<Player>(value = Player())

    suspend fun saveScore(playerId: Long) {
        var score = Score(playerId = playerId, score = attempts.size)
        scoreRepository.insertScore(score)
        Log.d("GameViewModel", "Saved score")
    }

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

    fun checkColors(secretColors: List<Color>): List<Color> {
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
        val feedback = checkColors(secretColors)
        if (!feedback.contains(Color.Gray) and !feedback.contains(Color.Yellow)) {
            isWin.value = true
        }
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

    fun getColorBase(numberOfColors: Long) {
        val allColors = listOf(
            Color.Red,
            Color.Green,
            Color.Blue,
            Color.Yellow,
            Color.Magenta,
            Color.Cyan,
            Color.Black,
            Color.White,
            Color.LightGray,
            Color.DarkGray
        )

        for (i in 0..<numberOfColors) {
            availableColors.add(allColors.get(i.toInt()))
        }
        secretColors.addAll(selectRandomColors(availableColors))
    }

    suspend fun getUserData(playerId: Long) {
        val player = playersRepository.getPlayerStream(playerId).first()
        if (player != null) {
            user.value = player
        }
    }
}
