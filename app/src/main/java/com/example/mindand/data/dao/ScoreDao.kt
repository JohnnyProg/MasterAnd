package com.example.mindand.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mindand.data.entity.Player
import com.example.mindand.data.entity.Score
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {
    //jeżeli zwraca Long to jest to id nowego obiektu
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: Score) : Long
    //są też adnotacje @Delete, @Update...
    //metoda, która zwraca Flow nie musi być wstrzymująca
    @Query("SELECT * from scores WHERE playerId = :playerId")
    fun getPlayerScoresStream(playerId: Int): Flow<List<Score>>
    //metoda, która nie zwraca Flow musi być wstrzymująca
    @Query("SELECT * from scores ORDER BY score ASC")
    suspend fun getScoresSorted(): List<Score>

    @Query("DELETE FROM scores")
    suspend fun deleteAllScores(): Unit
}