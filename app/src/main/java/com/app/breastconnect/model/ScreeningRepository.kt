package com.app.breastconnect.model

import com.app.breastconnect.data.ScreeningLocation

interface ScreeningRepository {
   suspend fun getScreeningLocationsByCounty(county: String): List<ScreeningLocation>
}