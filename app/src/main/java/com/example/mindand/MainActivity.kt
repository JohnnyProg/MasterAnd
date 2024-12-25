package com.example.mindand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mindand.presentation.gameScreen.GameScreen
import com.example.mindand.presentation.gameScreen.utils.selectRandomColors
import com.example.mindand.presentation.mainScreen.StartScreen
import com.example.mindand.presentation.scoresScreen.ScoreScreen

import com.example.mindand.ui.theme.MindAndTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindAndTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.StartScreen.path) {
        composable(
            Screen.StartScreen.path,
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    towards =  AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            }
        ) { StartScreen(navController) }
        composable(
            Screen.ProfileScreen.path,
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            }
        ) { backStackEntry ->

            ScoreScreen(navController)
        }
        composable(
            Screen.GameScreen.path+"/{playerId}/{numberOfColors}",
            arguments = listOf(
                navArgument("playerId") {type = NavType.LongType},
                navArgument("numberOfColors") {type = NavType.LongType}
            ),
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            }
        ) {backStackEntry ->
            val playerId = backStackEntry.arguments?.getLong("playerId") ?: 0L
            val numberOfColors = backStackEntry.arguments?.getLong("numberOfColors") ?: 6
            GameScreen(
                navController,
                playerId = playerId,
                numberOfColors = numberOfColors)
        }
    }
}



//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MindAndTheme {
//        Greeting("Android")
//    }
//}