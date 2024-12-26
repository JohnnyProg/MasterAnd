package com.example.mindand.data.di

import androidx.lifecycle.ViewModel
import com.example.mindand.data.dao.PlayerScoreDao
import com.example.mindand.data.dao.ScoreDao
import com.example.mindand.data.repository.ScoreRepository
import com.example.mindand.data.repository.ScoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
abstract class ScoresBindingModule {
    @Binds //tworzy metodę łączącą z interfejsem
    abstract fun bindScoresRepository(scoresRepositoryImpl: ScoreRepositoryImpl): ScoreRepository

}

@Module
@InstallIn(ViewModelComponent::class)
class ScoresModule {
    @Provides
    fun provideScoresRepository(scoresDao: ScoreDao, playerScoreDao: PlayerScoreDao) : ScoreRepositoryImpl {
        return ScoreRepositoryImpl(scoresDao, playerScoreDao)
    }
}