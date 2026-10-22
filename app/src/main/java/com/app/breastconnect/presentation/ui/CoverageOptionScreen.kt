package com.app.breastconnect.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.app.breastconnect.R
import com.app.breastconnect.presentation.CoverageOptionViewModel

@Composable
fun CoverageOptionScreen(
    onBack: () -> Unit,
    onFindClickOption : () -> Unit,
    coverageOptionViewmodel : CoverageOptionViewModel) {

    val coverageData by coverageOptionViewmodel.coverDataOption.collectAsState()
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()
        .paint(
            painter = painterResource(R.drawable.background_second),
            contentScale = ContentScale.FillBounds)
        .padding(10.dp).verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top

        ) {
        Spacer(modifier = Modifier.height(40.dp))
        coverageData?.coverageTitle?.let {
            Text(it,
                color = Color(0xFF38257C),
                style = MaterialTheme.typography.headlineMedium) }

        Spacer(modifier = Modifier.height(30.dp))

        Text ("Why I might need this?",
            color =Color(0xFF4836AA),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth().background(
                    color = Color(0xFFB382EC),
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        )
            .padding(horizontal = 16.dp, vertical = 8.dp))
         coverageData?.need ?.let {
           Text (it,
               color = Color.Black,
           style = MaterialTheme.typography.bodyLarge,
               modifier = Modifier.fillMaxWidth().background(
                   color = Color(0xFFF4E7FD)
               )
                   .padding(horizontal = 16.dp, vertical = 8.dp)

             )}

        Spacer(modifier = Modifier.height(30.dp))
        Text ("Coverage question to know:",
            color =Color(0xFF4836AA),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth().background(
                color = Color(0xFFF4E7FD),
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            )
                .padding(horizontal = 16.dp, vertical = 8.dp))
        coverageData?.question ?.let {
            Text (it,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth().background(
                    color = Color(0xFFFDEDF4)
                )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )}

        Spacer(modifier = Modifier.height(30.dp))
        Text ("Before your appointment:",
            color =Color(0xFF4836AA),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth().background(
                color = Color(0xFFBDB5F3),
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            )
                .padding(horizontal = 16.dp, vertical = 8.dp))
        coverageData?.checkList ?.let {
            Text (it,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth().background(
                    color = Color(0xFFF1ECFE)
                )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )}

        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.fillMaxWidth()
            .clickable { onFindClickOption() }
        ) {
            coverageData?.findString?.let {
                Text(
                    it,
                    color =Color(0xFF4836AA),
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }

}