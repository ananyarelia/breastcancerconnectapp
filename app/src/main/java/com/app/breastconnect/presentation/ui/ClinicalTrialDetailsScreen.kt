package com.app.breastconnect.presentation.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.breastconnect.presentation.ClinicalTrialDetailsViewModel
import androidx.core.net.toUri

private val Pink = Color(0xFFE95499)
private val Purple = Color(0xFF38257C)
private val LightPink = Color(0xFFFFEDF5)
private val Background = Color(0xFFFFF8FC)

@Composable
fun ClinicalTrialDetailsScreen(
    nctId: String,
    onBack: () -> Unit,
    viewModel: ClinicalTrialDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(nctId) {
        viewModel.loadTrial(nctId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding( top=40.dp, start=10.dp, end=10.dp)
    ) {

        Text(
            text = "Trial Details",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Purple
        )

        Spacer(Modifier.height(16.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator(color = Pink)
            }

            state.error != null -> {
                Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error
                )
                Button(onClick = { viewModel.loadTrial(nctId) }) {
                    Text("Retry")
                }
            }

            state.trial != null -> {
                val trial = state.trial!!

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = LightPink
                        ),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp),
                            verticalArrangement =
                                Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = trial.status.replace("_", " "),
                                color = Color(0xFF247A51),
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                text = trial.title,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = Purple
                            )

                            Text(
                                text = "NCT ID: ${trial.nctId}",
                                color = Color.Gray
                            )
                        }
                    }

                    DetailSection(
                        title = "About This Study",
                        content = trial.summary.ifBlank {
                            "Study summary is not available."
                        }
                    )

                    DetailSection(
                        title = "Eligibility",
                        content = buildString {
                            if (trial.minimumAge.isNotBlank()) {
                                appendLine("Minimum age: ${trial.minimumAge}")
                            }
                            if (trial.maximumAge.isNotBlank()) {
                                appendLine("Maximum age: ${trial.maximumAge}")
                            }
                            if (trial.sex.isNotBlank()) {
                                appendLine("Sex: ${trial.sex}")
                            }

                            appendLine()
                            append(
                                trial.eligibilityCriteria.ifBlank {
                                    "Eligibility criteria are not available."
                                }
                            )
                        }
                    )

                    DetailSection(
                        title = "Study Locations",
                        content = trial.location
                    )

                    Button(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                trial.url.toUri()
                            )
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Pink
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("View Official Trial ↗")
                    }

                    Text(
                        text = "Clinical trial information is for " +
                                "educational purposes. Contact the study " +
                                "team to confirm eligibility.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailSection(
    title: String,
    content: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Purple
            )

            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF645779)
            )
        }
    }
}