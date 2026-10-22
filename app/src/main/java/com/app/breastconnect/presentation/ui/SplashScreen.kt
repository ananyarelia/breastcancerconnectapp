package com.app.breastconnect.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.breastconnect.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private val SplashPurple = Color(0xFF38257C)
private val SplashPink = Color(0xFFE95499)
private val SplashBackground = Color(0xFFFFF8FC)

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(3000L.milliseconds)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SplashBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // BreastConnect app icon
            Box(
                modifier = Modifier
                    .size(210.dp)
                    .background(
                        Color(0xFFFFEAF3),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.logo
                    ),
                    contentDescription = "BreastConnect Logo",
                    modifier = Modifier.size(190.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Breast Cancer Connect",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = SplashPurple,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "New Jersey",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = SplashPink
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Breast Cancer Resources,\nScreening & Support",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color(0xFF76658C),
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 52.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "♥",
                fontSize = 26.sp,
                color = SplashPink
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Connecting you to care",
                fontSize = 13.sp,
                color = Color(0xFF9C88A7)
            )
        }
    }
}
