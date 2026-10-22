package com.app.breastconnect.presentation.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.breastconnect.R

private val DeepPurple = Color(0xFF38257C)
private val Pink = Color(0xFFD62980)
private val Lavender = Color(0xFF5533A9)

private data class HomeItem(
    val title: String,
    @DrawableRes val icon: Int,
    val arrowColor: Color,
    val arrowBackground: Color,
    val onClick: () -> Unit
)

@Composable
fun HomeScreen(
    onNavigateToFindScreening: () -> Unit,
    onNavigateToCoverage: () -> Unit,
    onNavigateToPolicies: () -> Unit,
    onNavigateToClinicalTrials: () -> Unit,
    onNavigateToStats: () -> Unit,
    onNavigateToResources: () -> Unit
) {
    val items = listOf(
        HomeItem(
            "Find Screening",
            R.drawable.find_screening_icon,
            Pink,
            Color(0xFFFFDDEE),
            onNavigateToFindScreening
        ),
        HomeItem(
            "Coverage",
            R.drawable.coverage,
            Lavender,
            Color(0xFFEDE2FF),
            onNavigateToCoverage
        ),
        HomeItem(
            "Policies",
            R.drawable.policies,
            Lavender,
            Color(0xFFEDE2FF),
            onNavigateToPolicies
        ),
        HomeItem(
            "Clinical Trials",
            R.drawable.clinical_trials,
            Pink,
            Color(0xFFFFDDEE),
            onNavigateToClinicalTrials
        ),
        HomeItem(
            "NJ Breast Cancer Stats",
            R.drawable.stats,
            Pink,
            Color(0xFFFFDDEE),
            onNavigateToStats
        ),
        HomeItem(
            "Resources",
            R.drawable.resources,
            Lavender,
            Color(0xFFEDE2FF),
            onNavigateToResources
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9FC))
            .paint(
                painter = painterResource(R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 42.dp,
                bottom = 52.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 22.dp,
                            bottom = 30.dp
                        )
                ) {
                    Text(
                        text = "Hi there!",
                        color = DeepPurple,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.8).sp
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "What are you looking for?",
                        color = Color(0xFF75649C),
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(20.dp))
                }
            }

            items(items.size) { index ->
                HomeCard(item = items[index])
            }
        }
    }
}

@Composable
private fun HomeCard(
    item: HomeItem
) {
    val state = rememberScrollState()
    Card(
        onClick = item.onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),

        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            // Nearly white, slightly translucent
            containerColor = Color.White.copy(alpha = 0.88f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 12.dp,
                    bottom = 14.dp
                ).verticalScroll(state)
        ) {
            // Large icon area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(item.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(2.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(Modifier.height(6.dp))
        }
    }
}
