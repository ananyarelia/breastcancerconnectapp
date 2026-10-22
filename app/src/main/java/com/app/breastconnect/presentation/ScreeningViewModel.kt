package com.app.breastconnect.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.breastconnect.data.ScreeningLocation
import com.app.breastconnect.model.ScreeningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreeningViewModel @Inject constructor(
    private val screeningRepository: ScreeningRepository
): ViewModel(){

    private val _screeningLocations = MutableStateFlow<List<ScreeningLocation>>(emptyList())
    val screeningLocations: StateFlow<List<ScreeningLocation>> = _screeningLocations.asStateFlow()

    private val _selectedCounty = MutableStateFlow("Select County")

    val selectedCounty: StateFlow<String> = _selectedCounty.asStateFlow()

    init {
        loadScreeningLocations()
    }

    private fun loadScreeningLocations() {
        viewModelScope.launch {
            _screeningLocations.value = screeningRepository.getScreeningLocationsByCounty(_selectedCounty.value)
        }
    }
    fun onCountySelected(county: String) {
        _selectedCounty.value = county
        viewModelScope.launch {
            val data = screeningRepository.getScreeningLocationsByCounty(county)
            _screeningLocations.value = data
        }
    }

}