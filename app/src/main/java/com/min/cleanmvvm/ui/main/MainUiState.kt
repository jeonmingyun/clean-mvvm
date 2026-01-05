package com.min.cleanmvvm.ui.main

import com.min.cleanmvvm.domain.model.User

data class MainUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList()
)
