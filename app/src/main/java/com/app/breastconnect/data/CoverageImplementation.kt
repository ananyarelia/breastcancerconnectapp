package com.app.breastconnect.data

import com.app.breastconnect.model.CoverageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CoverageImplementation @Inject constructor() : CoverageRepository {
    override fun getCoverageData(selectedOption: Int): Flow<CoverageData> {
        return fetchCoverageData(selectedOption)
    }

    fun fetchCoverageData(option: Int): Flow<CoverageData> {

        val coverageData = when (option) {
            1 -> CoverageData(
                "Screening Mammogram",
                "A screening mammogram uses low-dose X-rays to detect early signs " +
                        "of breast cancer in people who do not have breast symptoms. " +
                        "Regular screening can help detect breast cancer before " +
                        "symptoms develop.",
                "Screening mammograms are generally covered by most health " +
                        "insurance plans as preventive care, often without a copayment " +
                        "or deductible when eligibility requirements are met and " +
                        "an in-network provider is used. Coverage may vary based on " +
                        "age, screening frequency, and health plan.",
                "✓ Ask whether the facility is in-network\n" +
                        "✓ Confirm whether the screening is covered at no cost\n" +
                        "✓ Check if a doctor's order or referral is required\n" +
                        "✓ Ask whether 3D mammography is covered\n" +
                        "✓ Confirm any age or screening frequency restrictions",
                "Find screening mammography near me"
            )

            2 -> CoverageData(
                "Diagnostic Mammogram",
                "A diagnostic mammogram uses detailed X-ray images to evaluate " +
                        "breast symptoms, such as a lump, pain, or nipple discharge, " +
                        "or to investigate an abnormal screening mammogram result. " +
                        "Additional images may be taken to examine specific areas " +
                        "of concern.",
                "Diagnostic mammograms are often covered by health insurance " +
                        "when medically necessary. However, unlike preventive " +
                        "screening mammograms, diagnostic imaging may involve " +
                        "copayments, coinsurance, or deductibles depending on " +
                        "your health plan and applicable state or federal protections.",
                "✓ Ask whether the facility is in-network\n" +
                        "✓ Confirm whether prior authorization is required\n" +
                        "✓ Check if a doctor's order or referral is needed\n" +
                        "✓ Ask about copayments, deductibles, and coinsurance\n" +
                        "✓ Confirm whether additional imaging is covered",
                "Find diagnostic imaging near me"
            )
            3 -> CoverageData(
                "Breast Ultrasound",
                "Breast ultrasound uses sound waves to create images of breast tissue. " +
                        "It is commonly used to evaluate breast lumps, investigate abnormal " +
                        "mammogram findings, or provide additional imaging when recommended.",
                "Breast ultrasound is often covered by health insurance when medically " +
                        "necessary. Coverage and out-of-pocket costs may vary depending on " +
                        "whether it is diagnostic or supplemental screening.",
                "✓ Ask whether the facility is in-network\n" +
                        "✓ Ask whether prior authorization is required\n" +
                        "✓ Check if a referral or doctor's order is needed\n" +
                        "✓ Ask about estimated out-of-pocket costs",
                "Find breast ultrasound near me"
            )

            4 -> CoverageData(
                "Breast MRI",
                "Breast MRI uses magnetic fields and contrast-enhanced imaging to create " +
                        "detailed pictures of breast tissue. It may be recommended for " +
                        "people at high risk of breast cancer, further evaluation of " +
                        "abnormal findings, or assessment of known breast cancer.",
                "Breast MRI may be covered by health insurance when medically necessary, " +
                        "including certain high-risk screening situations. Coverage depends " +
                        "on your health plan, medical history, and whether prior " +
                        "authorization is required.",
                "✓ Ask whether the facility is in-network\n" +
                        "✓ Confirm whether prior authorization is required\n" +
                        "✓ Ask if your plan covers high-risk screening or diagnostic MRI\n" +
                        "✓ Check whether a doctor's order is required\n" +
                        "✓ Ask about estimated out-of-pocket costs",
                "Find breast MRI near me"
            )
            else -> CoverageData(
                "Screening Mammogram",
                "Screening mammogram is initial screening for breast cancer",
                "Screening mammogram is covered in most health plan",
                "Ask whether the facility is in Network",
                "Find screening mammography near me"
            )
        }
        return flowOf(coverageData)
    }
}

