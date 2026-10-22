package com.app.breastconnect.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.breastconnect.data.ClinicalTrial
import com.app.breastconnect.model.ClinicalTrialsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TrialsUiState(
    val condition: String = "Breast Cancer",
    val location: String = "New Jersey",
    val trials: List<ClinicalTrial> = emptyList(),
    val isLoading: Boolean = false,
    val hasSearched: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ClinicalTrialsViewModel @Inject constructor(
    private val clinicalTrialsRepository: ClinicalTrialsRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(TrialsUiState())
    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        searchTrials()
    }

    fun updateCondition(value: String) {
        _uiState.update {
            it.copy(condition = value)
        }
    }

    fun updateLocation(value: String) {
        _uiState.update {
            it.copy(location = value)
        }
    }

    fun searchTrials() {
        searchJob?.cancel()

        val condition = _uiState.value.condition.trim()
        val location = _uiState.value.location.trim()

        if (condition.isBlank() || location.isBlank()) {
            _uiState.update {
                it.copy(error = "Enter a condition and location.")
            }
            return
        }

        searchJob = viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null,
                    trials = emptyList()
                )
            }

            try {
                val results = clinicalTrialsRepository.searchTrials(
                    condition = condition,
                    location = location
                )

                _uiState.update {
                    it.copy(
                        trials = results,
                        isLoading = false,
                        hasSearched = true
                    )
                }

            } catch (e: CancellationException) {
                throw e

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hasSearched = true,
                        error = "Unable to load trials. Check your connection."
                    )
                }
            }
        }
    }
}