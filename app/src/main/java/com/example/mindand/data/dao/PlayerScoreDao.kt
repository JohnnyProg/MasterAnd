package com.example.mindand.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mindand.data.entity.PlayerWithScore
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerScoreDao {
    @Query("""
    SELECT 
        scores.scoreId AS scoreId,
        players.playerId AS playerId,
        players.name AS name,
        players.email AS email,
        scores.score AS score
    FROM players
    LEFT JOIN scores ON players.playerId = scores.playerId
    ORDER BY scores.score ASC
    """)
    suspend fun loadPlayersWithScores(): List<PlayerWithScore>
}