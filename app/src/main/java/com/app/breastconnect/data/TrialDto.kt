package com.app.breastconnect.data

data class TrialsResponse(
    val studies: List<StudyDto> = emptyList()
)

data class StudyDto(
    val protocolSection: ProtocolSectionDto
)

data class ProtocolSectionDto(
    val identificationModule: IdentificationDto,
    val statusModule: StatusDto,
    val contactsLocationsModule: ContactsLocationsDto? = null,
    val eligibilityModule: EligibilityDto? = null,
    val descriptionModule: DescriptionDto? = null
)

data class EligibilityDto(
    val eligibilityCriteria: String? = null,
    val sex: String? = null,
    val minimumAge: String? = null,
    val maximumAge: String? = null
)

data class DescriptionDto(
    val briefSummary: String? = null
)

data class IdentificationDto(
    val nctId: String,
    val briefTitle: String
)

data class StatusDto(
    val overallStatus: String
)

data class ContactsLocationsDto(
    val locations: List<LocationDto> = emptyList()
)

data class LocationDto(
    val facility: String? = null,
    val city: String? = null,
    val state: String? = null
)

data class ClinicalTrial(
    val nctId: String,
    val title: String,
    val status: String,
    val location: String,
    val url: String,
    val summary: String = "",
    val eligibilityCriteria: String = "",
    val sex: String = "",
    val minimumAge: String = "",
    val maximumAge: String = ""
)