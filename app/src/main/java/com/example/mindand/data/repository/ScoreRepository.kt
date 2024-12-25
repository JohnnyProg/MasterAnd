package com.example.mindand.data.repository

import com.example.mindand.data.entity.Player
import com.example.mindand.data.entity.PlayerWithScore
import com.example.mindand.data.entity.Score
import kotlinx.coroutines.flow.Flow

interface ScoreRepository {
    fun getPlayerScoresStream(id: Int): Flow<List<Score>>
    suspend fun getScoresSorted(): List<Score>
    suspend fun insertScore(score: Score) : Long
    suspend fun getPlayerScoreSorted(): List<PlayerWithScore>
    suspend fun deleteAllScores(): Unit
}