package com.example.mindand

sealed class Screen(val path: String) {
    object StartScreen : Screen("startScreen")
    object ProfileScreen: Screen("profileScreen") {

    }
    object GameScreen: Screen("gameScreen")
}