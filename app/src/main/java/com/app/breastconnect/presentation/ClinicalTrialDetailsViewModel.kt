package com.app.breastconnect.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.breastconnect.data.ClinicalTrial
import com.app.breastconnect.model.ClinicalTrialsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TrialDetailsUiState(
    val isLoading: Boolean = false,
    val trial: ClinicalTrial? = null,
    val error: String? = null
)

@HiltViewModel
class ClinicalTrialDetailsViewModel @Inject constructor(
    private val repository: ClinicalTrialsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TrialDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun loadTrial(nctId: String) {
        if (_uiState.value.trial?.nctId == nctId) return

        viewModelScope.launch {
            _uiState.value = TrialDetailsUiState(isLoading = true)

            try {
                val trial = repository.getTrialDetails(nctId)

                _uiState.value = TrialDetailsUiState(
                    trial = trial
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value = TrialDetailsUiState(
                    error = "Unable to load trial details."
                )
            }
        }
    }
}

