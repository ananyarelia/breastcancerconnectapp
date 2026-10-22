package com.app.breastconnect.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.breastconnect.presentation.PoliciesViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.app.breastconnect.R
import kotlin.collections.forEachIndexed

@Composable
fun PolicyOptionScreen(
    onBack: () -> Unit,
    policiesViewmodel: PoliciesViewModel
) {
    val selectedOption = policiesViewmodel.selectedOption.collectAsState().value
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = policiesViewmodel.policyDetailColor[selectedOption]
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, start = 10.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            Image(
                painter = painterResource(id = policiesViewmodel.policyDetailImage[selectedOption]),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = policiesViewmodel.policyOptions[selectedOption],
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF040278)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringArrayResource(policiesViewmodel.policyDetails)[selectedOption],
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF040278)
            )
            Spacer(modifier = Modifier.height(20.dp))
            KeyResources(policiesViewmodel, selectedOption)

        }
    }
}

@Composable
fun KeyResources(policiesViewmodel: PoliciesViewModel, selectedOption: Int) {
    Box(
        modifier = Modifier
            .background(
                color = policiesViewmodel.policyKeyResourcesBackgroundColor[selectedOption],
                shape = RoundedCornerShape(16.dp) // Sets all 4 corners to 16dp
            )
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "Key Resources",
                style = MaterialTheme.typography.headlineMedium,
                color = policiesViewmodel.policyKeyResourcesTitleColor[selectedOption]
            )
            Spacer(modifier = Modifier.height(20.dp))
            LearnMoreLinks(
                policiesViewmodel,
                selectedOption,
                policiesViewmodel.policyKeyResourcesTitleColor[selectedOption]
            )
        }
    }
}

@Composable
fun LearnMoreLinks(policiesViewmodel: PoliciesViewModel, selectedOption: Int, textColor: Color) {

    val keyResources: Array<String> =
        stringArrayResource(id = policiesViewmodel.policyTextLinks[selectedOption])
    val keyResourcesLinks: Array<String> =
        stringArrayResource(id = policiesViewmodel.policyUrlLinks[selectedOption])
    Column(modifier = Modifier.fillMaxWidth()) {
        keyResources.forEachIndexed { index, text ->
            val annotatedString = buildAnnotatedString {


                // Define the link configuration and styling
                val linkAnnotation = LinkAnnotation.Url(
                    url = keyResourcesLinks[index],
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            color = Color(0xFF000C7B),
                            textDecoration = TextDecoration.Underline
                        )
                    )
                )
                withLink(linkAnnotation) {
                    append(keyResources[index])
                }
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painterResource(id = R.drawable.link_icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(textColor),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = annotatedString, style = MaterialTheme.typography.titleMedium)

            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}