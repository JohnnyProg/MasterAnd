package com.example.mindand.presentation.mainScreen

import android.net.Uri
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mindand.AppViewModelProvider
import com.example.mindand.Screen
import com.example.mindand.presentation.mainScreen.components.ProfileImageWithPicker

@Composable
fun StartScreen(
    navController: NavHostController,
    viewModel: StartViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    var numOfColors by rememberSaveable { mutableStateOf("") }
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    val coroutineScope = rememberCoroutineScope()

    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = CubicBezierEasing(0.42f, 0f, 0.58f, 1f)
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "MasterAnd",
            fontSize = 32.sp, // Bazowy rozmiar tekstu
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .graphicsLayer(scaleX = scale, scaleY = scale)
        )

        ProfileImageWithPicker(imageUri) { uri -> imageUri = uri }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.name.value,
            onValueChange = { viewModel.name.value = it },
            label = { Text("name") }
        )

        OutlinedTextField(
            value = viewModel.email.value,
            onValueChange = { viewModel.email.value = it },
            label = { Text("Enter Email") }
        )
        OutlinedTextField(
            value = numOfColors,
            onValueChange = { numOfColors = it },
            label = { Text("Enter number of colors") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        //TODO Obrazek zle sie przesyla
        Button(
            onClick = {
                if (viewModel.name.value.isNotEmpty() && imageUri != null) {
                    val encoded = Uri.encode(imageUri.toString())
                    navController.navigate(Screen.GameScreen.path)
                }
            }
        ) {
            Text("Next")
        }
    }
}