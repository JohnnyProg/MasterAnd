package com.example.mindand.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mindand.data.entity.PlayerWithScore
import kotlinx.coroutines.flow.Flow

@Dao
interface playerScoreDao {
    @Query("SELECT players.playerId AS playerId, scores.scoreId AS scoreId FROM players, scores WHERE players.playerId = scores.playerId")
    fun loadPlayersWithScores(): Flow<List<PlayerWithScore>>
}