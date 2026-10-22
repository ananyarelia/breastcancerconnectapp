package com.app.breastconnect.presentation.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.breastconnect.R

private val DeepPurple = Color(0xFF38257C)
private val Pink = Color(0xFFE95499)
private val LightPink = Color(0xFFFFE8F1)
private val LightPurple = Color(0xFFF0E9FC)
private val ScreenBackground = Color(0xFFFFF8FC)

private data class ResourceItem(
    val title: String,
    val description: String,
    val url: String,
    @DrawableRes val imageRes: Int,
    val isPink: Boolean
)

private val resourceItems = listOf(
    ResourceItem(
        title = "Support Groups",
        description = "Connect with others who understand",
        url = "https://www.komen.org/support-resources/support/support-groups/",
        imageRes = R.drawable.support_groups,
        isPink = true
    ),
    ResourceItem(
        title = "Financial Assistance",
        description = "Help with treatment and living expenses",
        url = "https://www.komen.org/support-resources/financial-assistance/",
        imageRes = R.drawable.financial_assitance,
        isPink = false
    ),
    ResourceItem(
        title = "Counseling & Mental Health",
        description = "Emotional well-being and guidance",
        url = "https://www.komen.org/support-resources/support/social-support/",
        imageRes = R.drawable.mental,
        isPink = true
    ),
    ResourceItem(
        title = "Education & Information",
        description = "Breast health guides and learning materials",
        url = "https://www.komen.org/support-resources/tools/komen-education-materials/",
        imageRes = R.drawable.education,
        isPink = false
    ),
    ResourceItem(
        title = "Community Resources",
        description = "Find cancer services across New Jersey",
        url = "https://healthapps.nj.gov/cancer/cfSearch.aspx",
        imageRes = R.drawable.community,
        isPink = true
    ),
    ResourceItem(
        title = "Hotlines & Help",
        description = "Talk with a breast care navigator",
        url = "https://www.komen.org/support-resources/breast-cancer-helpline/",
        imageRes = R.drawable.hotline,
        isPink = false
    )
)

@Composable
fun ResourcesScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackground),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 40.dp,
            bottom = 32.dp
        ),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Resources",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = DeepPurple
                )

                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(
                        id = R.drawable.resources
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(42.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = LightPink
                )
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Support, information & care",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DeepPurple
                    )

                    Text(
                        text = "Find trusted resources to help " +
                                "you and your loved ones.",
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = Color(0xFF75658B)
                    )
                }
            }
        }

        items(
            items = resourceItems,
            key = { it.title }
        ) { resource ->
            ResourceCard(
                resource = resource,
                onClick = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(resource.url)
                    )

                    try {
                        context.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            context,
                            "No browser available to open this link.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            )
        }

        item {
            Text(
                text = "External resources open on their official websites.",
                fontSize = 12.sp,
                color = Color(0xFF8A7A93),
                modifier = Modifier.padding(
                    top = 8.dp,
                    bottom = 12.dp
                )
            )
        }
    }
}

@Composable
private fun ResourceCard(
    resource: ResourceItem,
    onClick: () -> Unit
) {
    val accentColor = if (resource.isPink) {
        Pink
    } else {
        DeepPurple
    }

    val iconBackground = if (resource.isPink) {
        LightPink
    } else {
        LightPurple
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier.size(72.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = resource.imageRes),
                    contentDescription = resource.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = resource.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 20.sp,
                    color = DeepPurple
                )

                Text(
                    text = resource.description,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = Color(0xFF81748E)
                )
            }

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        iconBackground,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "›",
                    fontSize = 29.sp,
                    lineHeight = 30.sp,
                    color = accentColor
                )
            }
        }
    }
}
