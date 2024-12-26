package com.example.mindand.presentation.mainScreen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mindand.data.entity.Player
import com.example.mindand.data.repository.PlayersRepository
import com.example.mindand.data.repository.ScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(private val playersRepository: PlayersRepository, private val scoresRepository: ScoreRepository) : ViewModel() {
    val playerId = mutableStateOf(0L)
    val name = mutableStateOf("")
    val email = mutableStateOf("")
    val numberOfColors = mutableStateOf("")

    suspend fun getAllPlayers() {
        val allPlayers = playersRepository.getAllPlayersStream().first()
        Log.d("StartViewModel", "All players: " + allPlayers.toString())
    }

    suspend fun savePlayer() {
        val existingPlayers = playersRepository.getPlayersByEmail(email.value)
        if(existingPlayers.isEmpty()) {
            val newPlayer = Player(name= name.value, email=email.value)
            playerId.value = playersRepository.insertPlayer(newPlayer)
            Log.d("StartViewModel", "Added new player with name ${newPlayer.name} and email ${newPlayer.email}")
            return
        }
        val existingPlayer = existingPlayers.first()
        val updatedPlayer = existingPlayer.copy(name = name.value)
        playersRepository.insertPlayer(updatedPlayer)
        playerId.value = updatedPlayer.playerId
        Log.d("StartViewModel", "PlayerId = ${playerId.toString()}")

    }

    suspend fun clearAllData() {
        scoresRepository.deleteAllScores()
        playersRepository.deleteAllPlayers()
    }
}