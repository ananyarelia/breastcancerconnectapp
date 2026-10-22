package com.app.breastconnect.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.breastconnect.data.CoverageData
import com.app.breastconnect.model.CoverageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CoverageOptionViewModel @Inject constructor(
    private val coverageRepository: CoverageRepository
) : ViewModel() {
    private val _selectedOption = MutableStateFlow<Int>(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val coverDataOption: StateFlow<CoverageData?> =
        _selectedOption.flatMapLatest() { option ->

            coverageRepository.getCoverageData(option)

        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun onOptionSelected(option: Int) {
        _selectedOption.value = option
    }

}