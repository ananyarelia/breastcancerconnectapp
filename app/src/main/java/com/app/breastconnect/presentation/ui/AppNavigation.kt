package com.app.breastconnect.presentation.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.breastconnect.presentation.ClinicalTrialsViewModel
import com.app.breastconnect.presentation.CoverageOptionViewModel
import com.app.breastconnect.presentation.PoliciesViewModel
import kotlinx.serialization.Serializable
import androidx.core.net.toUri

@Serializable
object SplashScreen

@Serializable
object Home
@Serializable
object FindScreening
@Serializable
object Coverage
@Serializable
object ClinicalTrials
@Serializable
object Policies
@Serializable
object CoverageOptionScreen

@Serializable
object PolicyOptionScreen
@Serializable
object ClinicalTrialDetailsScreen {
}
@Serializable
object Resources
@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = SplashScreen
    ) {
        composable<SplashScreen> {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Home) {
                        popUpTo(SplashScreen) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<Home> {
            HomeScreen(
                onNavigateToFindScreening = { navController.navigate(FindScreening) },
                onNavigateToCoverage = { navController.navigate(Coverage) },
                onNavigateToPolicies = { navController.navigate(Policies) },
                onNavigateToClinicalTrials = { navController.navigate(ClinicalTrials) },
                onNavigateToStats = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        "https://www-doh.nj.gov/doh-shad/indicator/view/BreastCaDth.Trend.html".toUri()
                    )
                    context.startActivity(intent)
                },
                onNavigateToResources = { navController.navigate(Resources) }
            )
        }
        composable<FindScreening> { FindScreening(onBack = { navController.popBackStack() },
            screeningViewModel = hiltViewModel()) }
        composable<Coverage> { backStackEntry ->
            val viewmodel: CoverageOptionViewModel = hiltViewModel(backStackEntry)
            Coverage(
                onBack = { navController.popBackStack() },
                onNavigateToCoverageOption = {
                    navController.navigate(route = CoverageOptionScreen)
                },
                coverageOptionViewModel = viewmodel
            )
        }
        composable<CoverageOptionScreen> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Coverage)
            }
            CoverageOptionScreen(
                onBack = { navController.popBackStack() },
                onFindClickOption = {navController.navigate(route = FindScreening)},
                coverageOptionViewmodel = hiltViewModel(parentEntry),
            )
        }
        composable<Policies> { backStackEntry ->
            val viewmodel: PoliciesViewModel = hiltViewModel(backStackEntry)
            Policies(
            onBack = { navController.popBackStack() },
            onPolicyOptionClick = { navController.navigate(route = PolicyOptionScreen)},
                policiesViewModel = viewmodel )
        }
        composable<PolicyOptionScreen> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Policies)
            }
            PolicyOptionScreen(
                onBack = { navController.popBackStack() },
                policiesViewmodel = hiltViewModel(parentEntry)
            )
        }
        composable<ClinicalTrials> {backStackEntry ->
            val viewmodel: ClinicalTrialsViewModel = hiltViewModel(backStackEntry)

            ClinicalTrials(onBack = { navController.popBackStack() },
                onTrialClick = { nctId ->
                    navController.navigate("trial_details/$nctId")
                }) }

        composable("trial_details/{nctId}") { backStackEntry ->

            val nctId = backStackEntry.arguments
                ?.getString("nctId")
                .orEmpty()

            ClinicalTrialDetailsScreen(
                nctId = nctId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<Resources> { ResourcesScreen(onBack = { navController.popBackStack() }) }
    }
}