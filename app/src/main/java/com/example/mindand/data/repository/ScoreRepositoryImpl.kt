package com.example.mindand.data.repository

import com.example.mindand.data.dao.PlayerScoreDao
import com.example.mindand.data.dao.ScoreDao
import com.example.mindand.data.entity.PlayerWithScore
import com.example.mindand.data.entity.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScoreRepositoryImpl @Inject constructor(private val scoreDao: ScoreDao, private val playerScoreDao: PlayerScoreDao) : ScoreRepository {
    override fun getPlayerScoresStream(id: Int): Flow<List<Score>> =
        scoreDao.getPlayerScoresStream(id)

    override suspend fun getScoresSorted(): List<Score> =
        scoreDao.getScoresSorted()

    override suspend fun insertScore(score: Score): Long =
        scoreDao.insert(score)

    override suspend fun getPlayerScoreSorted(): List<PlayerWithScore> =
        playerScoreDao.loadPlayersWithScores();

    override suspend fun deleteAllScores() =
        scoreDao.deleteAllScores()
}