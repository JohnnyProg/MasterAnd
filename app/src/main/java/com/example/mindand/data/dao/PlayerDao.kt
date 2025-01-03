package com.example.mindand.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mindand.data.entity.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    //jeżeli zwraca Long to jest to id nowego obiektu
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(player: Player) : Long
    //są też adnotacje @Delete, @Update...
    //metoda, która zwraca Flow nie musi być wstrzymująca
    @Query("SELECT * from players WHERE playerId = :playerId")
    fun getPlayerStream(playerId: Long): Flow<Player>
    //metoda, która nie zwraca Flow musi być wstrzymująca
    @Query("SELECT * from players WHERE email = :email")
    suspend fun getPlayersByEmail(email: String): List<Player>

    @Query("SELECT * from players")
    fun getAllPlayersStream(): Flow<List<Player>>

    @Query("DELETE FROM players")
    suspend fun deleteAllPlayers(): Unit
}