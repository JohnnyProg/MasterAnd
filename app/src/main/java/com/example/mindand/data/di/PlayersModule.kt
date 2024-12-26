package com.example.mindand.data.di

import com.example.mindand.data.dao.PlayerDao
import com.example.mindand.data.repository.PlayersRepository
import com.example.mindand.data.repository.PlayersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PlayerBindingModule {

    @Binds
    abstract fun bindPlayerRepository(playersRepositoryImpl: PlayersRepositoryImpl) : PlayersRepository
}

// PlayersModule
@Module
@InstallIn(ViewModelComponent::class)
class PlayersModule {
    @Provides
    fun providePlayersRepository(playerDao: PlayerDao): PlayersRepositoryImpl {
        return PlayersRepositoryImpl(playerDao)
    }
}