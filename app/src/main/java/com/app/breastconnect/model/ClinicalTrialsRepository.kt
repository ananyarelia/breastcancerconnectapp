package com.app.breastconnect.model

import com.app.breastconnect.data.ClinicalTrial

interface ClinicalTrialsRepository {
    suspend fun searchTrials(
        condition: String,
        location: String
    ): List<ClinicalTrial>

    suspend fun getTrialDetails(
        nctId: String
    ): ClinicalTrial
}