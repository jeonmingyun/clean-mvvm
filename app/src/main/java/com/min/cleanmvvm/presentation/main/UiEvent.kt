package com.min.cleanmvvm.presentation.main

// 1회성 이벤트를 위한 Sealed Class
sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
}
