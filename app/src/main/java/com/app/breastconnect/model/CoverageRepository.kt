package com.app.breastconnect.model

import com.app.breastconnect.data.CoverageData
import kotlinx.coroutines.flow.Flow

interface CoverageRepository {

    fun getCoverageData(selectedOption: Int) : Flow<CoverageData>

}