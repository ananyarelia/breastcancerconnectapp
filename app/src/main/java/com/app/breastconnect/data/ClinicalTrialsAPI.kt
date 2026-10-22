package com.app.breastconnect.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClinicalTrialsApi {

    @GET("api/v2/studies")
    suspend fun getClinicalTrials(
        @Query("query.cond") condition: String,
        @Query("query.locn") location: String,
        @Query("filter.overallStatus") status: String = "RECRUITING",
        @Query("pageSize") pageSize: Int = 20
    ): TrialsResponse

    @GET("api/v2/studies/{nctId}")
    suspend fun getTrialDetails(
        @Path("nctId") nctId: String
    ): StudyDto
}