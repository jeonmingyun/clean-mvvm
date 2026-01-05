package com.min.cleanmvvm.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.min.cleanmvvm.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<MainUiEvent>()
    val uiEvent: SharedFlow<MainUiEvent> = _uiEvent.asSharedFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val users = getUsersUseCase()

            _uiState.update {
                it.copy(
                    isLoading = false,
                    users = users
                )
            }

            _uiEvent.emit(MainUiEvent.ShowToast)
        }
    }
}
