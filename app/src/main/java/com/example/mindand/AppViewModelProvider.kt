package com.example.mindand

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mindand.presentation.gameScreen.GameViewModel
import com.example.mindand.presentation.mainScreen.StartViewModel
import com.example.mindand.presentation.scoresScreen.ScoreViewModel

//object AppViewModelProvider {
//    val Factory = viewModelFactory {
//        initializer {
//            StartViewModel(masterAndApplication().container.playersRepository, masterAndApplication().container.scoreRepository)
//        }
//        initializer {
//            ScoreViewModel(masterAndApplication().container.scoreRepository)
//        }
//        initializer {
//            GameViewModel(masterAndApplication().container.scoreRepository, masterAndApplication().container.playersRepository)
//        }
//        //tutaj dodać inicializator dla każdego ViewModelu...
//    }
//
//}

//fun CreationExtras.masterAndApplication(): MasterAndApplication =
//    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
//            MasterAndApplication)