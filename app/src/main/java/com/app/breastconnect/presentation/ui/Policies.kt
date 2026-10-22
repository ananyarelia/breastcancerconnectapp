package com.app.breastconnect.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.breastconnect.R
import com.app.breastconnect.presentation.PoliciesViewModel

@Composable
fun Policies(
    onBack: () -> Unit,
    onPolicyOptionClick: () -> Unit,
    policiesViewModel: PoliciesViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background_second),
                contentScale = ContentScale.FillBounds
            )
            .padding(top = 40.dp, start = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        //Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.policies_heading),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            items(policiesViewModel.policyOptions.size) { index ->

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Card(
                        onClick = {
                            onPolicyOptionClick()
                            policiesViewModel.onOptionSelected(index)
                        },
                        elevation = CardDefaults.cardElevation(8.dp),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .padding(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = policiesViewModel.policyColor[index]
                        )
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = policiesViewModel.policyIcons[index]),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(end = 10.dp)

                            )
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = policiesViewModel.policyOptions[index],
                                    color = Color(0xFF1F249F),
                                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = policiesViewModel.policyDescriptions[index],
                                    color = Color(0xFF131492),
                                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
