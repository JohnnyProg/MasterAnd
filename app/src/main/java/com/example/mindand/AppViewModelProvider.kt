package com.example.mindand

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.mindand.presentation.mainScreen.StartViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
//        initializer {
//            ProfileViewModel(masterAndApplication().container.playersRepository)
//        }
        initializer {
            StartViewModel(MasterAndApplication().container.playersRepository)
        }
        //tutaj dodać inicializator dla każdego ViewModelu...
    }

}