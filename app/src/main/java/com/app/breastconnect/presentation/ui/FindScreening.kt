package com.app.breastconnect.presentation.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.breastconnect.R
import com.app.breastconnect.presentation.ScreeningViewModel
import androidx.core.net.toUri


@Composable
fun FindScreening(
    onBack: () -> Unit,
    screeningViewModel: ScreeningViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_second),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Image(
            modifier = Modifier.padding(top = 40.dp),
            painter = painterResource(id = R.drawable.find_screening_header),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(10.dp))
        SelectCounty(screeningViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        ScreeningLocationsList(screeningViewModel)
    }
}

@Composable
fun ScreeningLocationsList(screeningViewModel: ScreeningViewModel) {

    val screeningLocations by screeningViewModel.screeningLocations.collectAsState()
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        items(screeningLocations.size) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(2f)
                    ) {
                        Column(modifier = Modifier.padding(start = 10.dp)) {
                            Text(
                                text = screeningLocations[index].name,
                                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                                color = Color(0xFF27127F)
                            )
                            Text(
                                text = "MAMMOGRAPHY CENTER",
                                modifier = Modifier.background(color = Color(0xFFFCE9F1)),
                                color = Color(0xFFE572B4)
                            )
                            Text(
                                text = screeningLocations[index].address,
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                                color = Color.Black

                            )
                            Text(
                                text = screeningLocations[index].phone,
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                            if (screeningLocations[index].is3DScreeningAvailable) {
                                Log.d("ScreeningLocationsList", "3D Screening Available")
                                Text(
                                    text = "3D Screening Available",
                                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                                    color = Color.Black
                                )
                            }
                            if (screeningLocations[index].diagnosticMammography) {
                                Text(
                                    text = "Diagnostic Mammography",
                                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                                )
                            }
                            if (screeningLocations[index].breastMRI) {
                                Text(
                                    text = "Breast MRI",
                                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                                )
                            }
                            if (screeningLocations[index].breastUltrasound) {
                                Text(
                                    text = "Breast Ultrasound",
                                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                                )
                            }
                            if (screeningLocations[index].isNJCEEDAvailable) {
                                Text(
                                    text = "NJCEED Available",
                                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {  // Create an intent to launch the dialer with the phone number
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = "tel:${screeningLocations[index].phone}".toUri()
                            }

                            context.startActivity(intent) }) {
                            Image(
                                painterResource(id = R.drawable.call_icon),
                                contentDescription = null
                            )
                        }
                        IconButton(onClick = {
                            openMap(context, screeningLocations[index].address)
                        }) {
                            Image(
                                painterResource(id = R.drawable.map_icon),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCounty(viewModel: ScreeningViewModel) {

    val counties = listOf(
        "All", "Atlantic", "Bergen", "Burlington", "Camden",
        "Cape May", "Cumberland", "Essex", "Gloucester",
        "Hudson", "Hunterdon", "Mercer", "Middlesex", "Monmouth",
        "Morris", "Ocean", "Passaic", "Salem", "Somerset",
        "Sussex", "Union", "Warren"
    )

    var expanded by remember { mutableStateOf(false) }
    val selectedOption by viewModel.selectedCounty.collectAsState()

    val deepPurple = Color(0xFF38257C)
    val lightPink = Color(0xFFFFE6F0)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor(
                        ExposedDropdownMenuAnchorType.PrimaryNotEditable
                    )
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),

                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },

                textStyle = MaterialTheme.typography.titleMedium,

                colors = TextFieldDefaults.colors(
                    focusedTextColor = deepPurple,
                    unfocusedTextColor = deepPurple,

                    focusedContainerColor = lightPink,
                    unfocusedContainerColor = lightPink,
                    disabledContainerColor = lightPink,

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,

                    focusedTrailingIconColor = deepPurple,
                    unfocusedTrailingIconColor = deepPurple
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },

                // Fix the black dropdown background
                modifier = Modifier
                    .background(
                        Color.White,
                        RoundedCornerShape(12.dp)
                    ),

                // Explicit Material 3 menu surface color
                containerColor = Color.White,

                shape = RoundedCornerShape(12.dp),

                shadowElevation = 6.dp
            ) {
                counties.forEach { county ->

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = county,
                                color = deepPurple,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        onClick = {
                            viewModel.onCountySelected(county)
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = deepPurple
                        )
                    )
                }
            }
        }
    }
}


// Helper function to build and launch the geo intent
private fun openMap(context: Context, location: String) {
    val encodedLocation = Uri.encode(location)

    val mapUri = "geo:0,0?q=$encodedLocation".toUri()

    val mapIntent = Intent(Intent.ACTION_VIEW, mapUri).apply {
        setPackage("com.google.android.apps.maps")
        addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    }

    try {
        context.startActivity(mapIntent)
    } catch (e: ActivityNotFoundException) {
        // Open Google Maps in a browser if the app is unavailable
        val webUri =
            "https://www.google.com/maps/search/?api=1&query=$encodedLocation".toUri()

        val webIntent = Intent(Intent.ACTION_VIEW, webUri)
        context.startActivity(webIntent)
    }
}