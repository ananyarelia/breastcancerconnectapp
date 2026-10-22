package com.app.breastconnect.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.breastconnect.R
import com.app.breastconnect.presentation.CoverageOptionViewModel

@Composable
fun Coverage(
    onBack: () -> Unit,
    onNavigateToCoverageOption: () -> Unit,
    coverageOptionViewModel: CoverageOptionViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_second),
                contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text ="Know Your Coverage",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color =Color(0xFF38257C)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text ="Click on the card to know more",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color =Color(0xFF38257C)
        )
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(scrollState).padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start) {
                Card(
                    onClick = {
                        coverageOptionViewModel.onOptionSelected(1)
                        onNavigateToCoverageOption()
                    },
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.screeningmammogram),
                        contentDescription = null
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End) {
                Card(
                    onClick = {
                        coverageOptionViewModel.onOptionSelected(2)
                        onNavigateToCoverageOption()
                    },
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.diagnosticmammogram),
                        contentDescription = null
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start) {
                Card(
                    onClick = {
                        coverageOptionViewModel.onOptionSelected(3)
                        onNavigateToCoverageOption()
                    },
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.breastultrasound),
                        contentDescription = null
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End) {
                Card(
                    onClick = {
                        coverageOptionViewModel.onOptionSelected(4)
                        onNavigateToCoverageOption()
                    },
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(6.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.breastmri),
                        contentDescription = null
                    )
                }
            }
        }
    }
}