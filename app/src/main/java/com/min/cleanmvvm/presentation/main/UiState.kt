package com.min.cleanmvvm.presentation.main

import com.min.cleanmvvm.domain.model.User

// UI 상태를 나타내는 Sealed Class
sealed class UiState {
    object Loading : UiState()
    data class Success(val users: List<User>) : UiState()
    data class Error(val message: String) : UiState()
}