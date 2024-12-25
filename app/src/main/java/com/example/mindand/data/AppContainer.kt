package com.example.mindand.data

import android.content.Context
import com.example.mindand.data.repository.PlayersRepository
import com.example.mindand.data.repository.PlayersRepositoryImpl
import com.example.mindand.data.repository.ScoreRepository
import com.example.mindand.data.repository.ScoreRepositoryImpl

interface AppContainer {
    val playersRepository: PlayersRepository
    val scoreRepository: ScoreRepository
    //tutaj dodać deklarację wszystkich repozytoriów...
}
class AppDataContainer(private val context: Context) : AppContainer {
    override val playersRepository: PlayersRepository by lazy {
        PlayersRepositoryImpl(HighScoreDatabase.getDatabase(context).playerDao())
    }
    override val scoreRepository: ScoreRepository by lazy {
        ScoreRepositoryImpl(
            HighScoreDatabase.getDatabase(context).scoreDao(),
            HighScoreDatabase.getDatabase(context).playerScoreDao()
            )
    }
    //tutaj dodać implementację wszystkich repozytoriów...
}