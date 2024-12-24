package com.example.mindand.presentation.mainScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mindand.data.entity.Player
import com.example.mindand.data.repository.PlayersRepository

class StartViewModel(private val playersRepository: PlayersRepository) : ViewModel() {
    val playerId = mutableStateOf(0L)
    val name = mutableStateOf("")
    val email = mutableStateOf("")

    suspend fun savePlayer() {
        val existingPlayers = playersRepository.getPlayersByEmail(email.value)
        if(existingPlayers.isEmpty()) {
            val newPlayer = Player(name= name.value, email=email.value)
            playersRepository.insertPlayer(newPlayer)
            return
        }
        val existingPlayer = existingPlayers.first()
        val updatedPlayer = existingPlayer.copy(name = name.value)
        playersRepository.insertPlayer(updatedPlayer)
        playerId.value = updatedPlayer.playerId

    }
}