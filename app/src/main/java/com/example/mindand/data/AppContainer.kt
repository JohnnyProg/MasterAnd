package com.example.mindand.data

import android.content.Context
import com.example.mindand.data.repository.PlayersRepository
import com.example.mindand.data.repository.PlayersRepositoryImpl

interface AppContainer {
    val playersRepository: PlayersRepository
    //tutaj dodać deklarację wszystkich repozytoriów...
}
class AppDataContainer(private val context: Context) : AppContainer {
    override val playersRepository: PlayersRepository by lazy {
        PlayersRepositoryImpl(HighScoreDatabase.getDatabase(context).playerDao())
    }
    //tutaj dodać implementację wszystkich repozytoriów...
}