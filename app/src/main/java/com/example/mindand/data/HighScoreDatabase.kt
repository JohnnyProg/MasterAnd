package com.example.mindand.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mindand.data.dao.PlayerDao
import com.example.mindand.data.dao.PlayerScoreDao
import com.example.mindand.data.dao.ScoreDao
import com.example.mindand.data.entity.Player
import com.example.mindand.data.entity.Score

@Database(
    entities = [Score::class, Player::class],
    version = 1,
    exportSchema = false
)
abstract class HighScoreDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun playerScoreDao(): PlayerScoreDao
    abstract fun scoreDao(): ScoreDao
    //dodać wszystkie Dao...
    companion object {
        @Volatile
        private var Instance: HighScoreDatabase? = null
        fun getDatabase(context: Context): HighScoreDatabase {
            return Room.databaseBuilder(
                context,
                HighScoreDatabase::class.java,
                "highscore_database"
            )
                .build().also { Instance = it }
        }
    }
}