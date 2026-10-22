package com.app.breastconnect.data

import com.app.breastconnect.model.ClinicalTrialsRepository
import javax.inject.Inject

class ClinicalTrialsImplementation @Inject constructor(
    private val api: ClinicalTrialsApi
) : ClinicalTrialsRepository {

    override suspend fun searchTrials(
        condition: String,
        location: String
    ): List<ClinicalTrial> {

        return api.getClinicalTrials(
            condition = condition,
            location = location
        ).studies.map { study ->

            val protocol = study.protocolSection

            val locations = protocol.contactsLocationsModule
                ?.locations.orEmpty()

            val preferredLocation = locations.firstOrNull {
                it.state.equals(
                    "New Jersey",
                    ignoreCase = true
                )
            } ?: locations.firstOrNull()

            val locationText = preferredLocation?.let {
                listOfNotNull(
                    it.facility,
                    it.city,
                    it.state
                )
                    .filter(String::isNotBlank)
                    .joinToString(", ")
            }.orEmpty().ifBlank {
                "Location not listed"
            }

            ClinicalTrial(
                nctId = protocol.identificationModule.nctId,
                title = protocol.identificationModule.briefTitle,
                status = protocol.statusModule.overallStatus,
                location = locationText,
                url = "https://clinicaltrials.gov/study/" +
                        protocol.identificationModule.nctId
            )
        }
    }

    override suspend fun getTrialDetails(
        nctId: String
    ): ClinicalTrial {

        val study = api.getTrialDetails(nctId)
        val protocol = study.protocolSection
        val eligibility = protocol.eligibilityModule

        val locations = protocol.contactsLocationsModule
            ?.locations.orEmpty()

        val locationText = locations
            .filter {
                it.state.equals("New Jersey", ignoreCase = true)
            }
            .ifEmpty { locations }
            .map {
                listOfNotNull(it.facility, it.city, it.state)
                    .filter(String::isNotBlank)
                    .joinToString(", ")
            }
            .filter { it.isNotBlank() }
            .distinct()
            .joinToString("\n")

        return ClinicalTrial(
            nctId = protocol.identificationModule.nctId,
            title = protocol.identificationModule.briefTitle,
            status = protocol.statusModule.overallStatus,
            location = locationText.ifBlank { "Location not listed" },
            summary = protocol.descriptionModule
                ?.briefSummary.orEmpty(),
            eligibilityCriteria = eligibility
                ?.eligibilityCriteria.orEmpty(),
            sex = eligibility?.sex.orEmpty(),
            minimumAge = eligibility?.minimumAge.orEmpty(),
            maximumAge = eligibility?.maximumAge.orEmpty(),
            url = "https://clinicaltrials.gov/study/" +
                    protocol.identificationModule.nctId
        )
    }
}