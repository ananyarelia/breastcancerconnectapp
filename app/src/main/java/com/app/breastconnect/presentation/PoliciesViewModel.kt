package com.app.breastconnect.presentation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.lifecycle.ViewModel
import com.app.breastconnect.R
import com.app.breastconnect.model.PoliciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PoliciesViewModel @Inject constructor(
    private val policiesRepository: PoliciesRepository
): ViewModel() {

    private val _selectedOption = MutableStateFlow<Int>(0)
    val policyOptions = listOf(
        "Breast Cancer Screening",
        "NJ Insurance & Coverage",
        "NJ Breast Cancer Programs",
        "Patient Rights & Protections",
        "Recent Policy Updates"
    )
    val policyDescriptions = listOf(
        "Learn about what screening services are covered and who may qualify.",
        "Explore programs like GetCoveredNJ, NJFamilyCare and financial assistance options.",
        "Learn about NJCEED, Medicaid and other state cancer-control programs.",
        "Understand your insurance coverage, preventive care requirements and your rights as a patient.",
        "Stay informed about new legislation, changes to screening requirements and important announcements"
    )
    val policyIcons = listOf(
        R.drawable.breastcancerscreening,
        R.drawable.njinsuranceandcoverage,
        R.drawable.njbreastcancerprograms,
        R.drawable.patient_rights,
        R.drawable.recent_policies_updates
    )

    val policyColor = listOf(
        Color(0xFFFCEBF4),
        Color(0xFFF1EBFE),
        Color(0xFFFDECF4),
        Color(0xFFF1EBFE),
        Color(0xFFFDE8F4)
    )
    val policyDetailColor = listOf(
        Color(0xFFFCE9F1),
        Color(0xFFEAE9FE),
        Color(0xFFFCE9F1),
        Color(0xFFECEAFE),
        Color(0xFFFDE5F1)
    )

    val policyDetailImage = listOf(
        R.drawable.policy_option_1,
        R.drawable.policy_option_2,
        R.drawable.policy_option_3,
        R.drawable.policy_option_4,
        R.drawable.policy_option_5    )
    val policyTextLinks = listOf(
        R.array.policy_option_1_text_links,
        R.array.policy_option_2_text_links,
        R.array.policy_option_3_text_links,
        R.array.policy_option_4_text_links,
        R.array.policy_option_5_text_links
    )
    val policyUrlLinks = listOf(
        R.array.policy_option_1_url_links,
        R.array.policy_option_2_url_links,
        R.array.policy_option_3_url_links,
        R.array.policy_option_4_url_links,
        R.array.policy_option_5_url_links
    )

    val policyKeyResourcesBackgroundColor = listOf(
        Color(0xFFFCD6EE),
        Color(0xFFE1DEFE),
        Color(0xFFFCD6EE),
        Color(0xFFE1DEFE),
        Color(0xFFFCD6EE)

    )

    val policyKeyResourcesTitleColor = listOf(
        Color(0xFF71043F),
        Color(0xFF5E29BD),
        Color(0xFFC00959),
        Color(0xFF592CBB),
        Color(0xFFC00959)
    )
    val policyDetails = R.array.policy_details
    val selectedOption : StateFlow<Int> = _selectedOption

    fun onOptionSelected(option: Int) {
        _selectedOption.value = option
    }
}