package com.app.breastconnect.di

import com.app.breastconnect.data.ClinicalTrialsImplementation
import com.app.breastconnect.data.CoverageImplementation
import com.app.breastconnect.data.PoliciesImplementation
import com.app.breastconnect.data.ScreeningImplementation
import com.app.breastconnect.model.ClinicalTrialsRepository
import com.app.breastconnect.model.CoverageRepository
import com.app.breastconnect.model.PoliciesRepository
import com.app.breastconnect.model.ScreeningRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindScreeningRepository(
        screeningImplementation: ScreeningImplementation
    ): ScreeningRepository

    @Binds
    @Singleton
    abstract fun bindCoverageRepository(
        coverageImplementation: CoverageImplementation
    ): CoverageRepository

    @Binds
    @Singleton
    abstract fun bindClinicalTrialsRepository(
        clinicalTrialsImplementation: ClinicalTrialsImplementation
    ): ClinicalTrialsRepository

    @Binds
    @Singleton
    abstract fun bindPoliciesRepository(
        policiesImplementation: PoliciesImplementation
    ): PoliciesRepository

}

