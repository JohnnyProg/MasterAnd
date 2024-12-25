package com.example.mindand.data.repository

import com.example.mindand.data.dao.PlayerDao
import com.example.mindand.data.entity.Player
import kotlinx.coroutines.flow.Flow

class PlayersRepositoryImpl(private val playerDao: PlayerDao) : PlayersRepository {
    override fun getAllPlayersStream(): Flow<List<Player>> =
        playerDao.getAllPlayersStream()

    override fun getPlayerStream(playerId: Long): Flow<Player?> =
        playerDao.getPlayerStream(playerId)

    override suspend fun getPlayersByEmail(email: String): List<Player> =
        playerDao.getPlayersByEmail(email)

    override suspend fun insertPlayer(player: Player): Long =
        playerDao.insert(player)

    override suspend fun deleteAllPlayers() =
        playerDao.deleteAllPlayers()
}