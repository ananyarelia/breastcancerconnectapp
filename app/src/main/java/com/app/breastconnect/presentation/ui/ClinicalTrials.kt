package com.app.breastconnect.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.breastconnect.R
import com.app.breastconnect.data.ClinicalTrial
import com.app.breastconnect.presentation.ClinicalTrialsViewModel


@Composable
fun ClinicalTrials(
    onBack: () -> Unit = {},
    onTrialClick: (String) -> Unit,
    viewModel: ClinicalTrialsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_second),
                contentScale = ContentScale.FillBounds
            )
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Clinical Trials",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF351C8E)
        )

        Text(
            text = "Explore breast cancer research studies",
            color = Color.Black
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.condition,
            onValueChange = viewModel::updateCondition,
            label = { Text("Condition", color = Color.Black) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFF351C8E),      // Color when focused
                unfocusedTextColor = Color(0xFF351C8E) // Color when not focused
            )
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.location,
            onValueChange = viewModel::updateLocation,
            label = { Text("Location", color = Color.Black) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color(0xFF351C8E),      // Color when focused
                unfocusedTextColor = Color(0xFF351C8E) //
            )
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = viewModel::searchTrials,
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF3B8B)
            )
        ) {
            Text("Search Trials")
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Recruiting Studies",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF351C8E),
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        if (state.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFFF3B8B)
            )
        }

        state.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        if (
            state.hasSearched &&
            !state.isLoading &&
            state.error == null &&
            state.trials.isEmpty()
        ) {
            Text("No matching trials found.")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(
                items = state.trials,
                key = { it.nctId }
            ) { trial ->
                TrialCard(
                    trial = trial,
                    onClick = {
                        onTrialClick(trial.nctId)
                    })
            }
        }
    }
}

@Composable
fun TrialCard(
    trial: ClinicalTrial,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFEDF5)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = trial.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF351C8E)
            )

            Text(
                text = trial.nctId,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )

            Text(
                text = trial.status.replace("_", " "),
                color = Color(0xFF248050),
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = trial.location,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black
            )

            HorizontalDivider(
                color = Color(0xFFF0D3E3)
            )

            Text(
                text = "View Trial Details →",
                color = Color(0xFFFF3B8B),
                fontWeight = FontWeight.Medium
            )
        }
    }
}
