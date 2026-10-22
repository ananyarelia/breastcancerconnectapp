package com.app.breastconnect.di

import com.app.breastconnect.data.ClinicalTrialsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClinicalTrialsNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://clinicaltrials.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideClinicalTrialsApi(
        retrofit: Retrofit
    ): ClinicalTrialsApi {
        return retrofit.create(
            ClinicalTrialsApi::class.java
        )
    }
}