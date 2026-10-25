package com.app.breastconnect.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.app.breastconnect.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(3000L.milliseconds)
        onSplashFinished()
    }

    Image(
        painter = painterResource(
            id = R.drawable.pinkpath_splash
        ),
        contentDescription = "PinkPath Splash Screen",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}
