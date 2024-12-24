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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mindand.presentation.gameScreen.GameScreen
import com.example.mindand.presentation.gameScreen.utils.selectRandomColors
import com.example.mindand.presentation.mainScreen.StartScreen
import com.example.mindand.presentation.scoresScreen.ProfileScreen

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

    NavHost(navController = navController, startDestination = Screen.GameScreen.path) {
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
            Screen.ProfileScreen().path,
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
            val name = backStackEntry.arguments?.getString(Screen.ProfileScreen().name) ?: ""
            val imageUri = backStackEntry.arguments?.getString(Screen.ProfileScreen().imageUri)
            ProfileScreen(navController, name, imageUri)
        }
        composable(
            Screen.GameScreen.path,
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
        ) { GameScreen(
            availableColors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta, Color.Cyan),
            secretColors = selectRandomColors(listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta, Color.Cyan))) {
            // Handle game end
        } }
    }
}


@Preview
@Composable
fun ProfileScreenInitialPreview() {
    MindAndTheme {
        val navController = rememberNavController()
        ProfileScreen(navController = navController, name = "Radek", imageUri = "")
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