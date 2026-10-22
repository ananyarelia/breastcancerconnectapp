package com.app.breastconnect.data

import android.content.Context
import com.app.breastconnect.model.ScreeningRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ScreeningImplementation @Inject constructor(
    @ApplicationContext private val context: Context)
    : ScreeningRepository {

    // Helper function to read asset file
    private fun readJsonFromAssets(): String {
        return context.assets.open(
            "breastconnect_nj_screening_locations.json"
        )
            .bufferedReader().use { it.readText() }
    }

    private fun getAllScreeningLocations(): List<ScreeningLocation> {
        return try {
            val jsonString = readJsonFromAssets()
            val listType = object : TypeToken<List<ScreeningLocation>>() {}.type
            Gson().fromJson(jsonString, listType)
        } catch (_: Exception) {
            emptyList()
        }
    }

    override suspend fun getScreeningLocationsByCounty(county: String): List<ScreeningLocation> {
        val screeningLocations = getAllScreeningLocations()
        if(county == "Select County" || county == "All") return screeningLocations
        return screeningLocations.filter { it.county == county }
    }
}