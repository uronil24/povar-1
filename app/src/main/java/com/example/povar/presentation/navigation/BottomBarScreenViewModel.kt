package com.example.povar.presentation.navigation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.povar.domain.usecase.preference.GetPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomBarScreenViewModel @Inject constructor(
    private val getPreferencesUseCase: GetPreferencesUseCase
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    init {
        load()
    }

    private fun load(){
        viewModelScope.launch {
            getPreferencesUseCase.get().collect{
                _query.value = it
            }
        }
    }
}