package com.example.mindand.data.di

import android.content.Context
import com.example.mindand.data.HighScoreDatabase
import com.example.mindand.data.dao.PlayerDao
import com.example.mindand.data.dao.PlayerScoreDao
import com.example.mindand.data.dao.ScoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    //metoda łącząca zwracająca obiekt
    @Provides
    fun providesScoreDao(highScoreDatabase: HighScoreDatabase): ScoreDao {
        return highScoreDatabase.scoreDao()
    }
    @Provides
    fun providesPlayerDao(highScoreDatabase: HighScoreDatabase): PlayerDao {
        return highScoreDatabase.playerDao()
    }
    @Provides
    fun providesPlayerScoreDao(highScoreDatabase: HighScoreDatabase): PlayerScoreDao {
        return highScoreDatabase.playerScoreDao()
    }

    @Provides
    @Singleton //instancja tworzona tylko raz
    fun provideHighScoreDatabase(
        @ApplicationContext applicationContext: Context //wstrzykiwanie kontekstu
                                                        //aplikacji
    ): HighScoreDatabase {
        return HighScoreDatabase.getDatabase(applicationContext)
    }

}