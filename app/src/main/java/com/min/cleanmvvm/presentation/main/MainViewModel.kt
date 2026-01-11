package com.min.cleanmvvm.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.min.cleanmvvm.domain.model.User
import com.min.cleanmvvm.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val fetchAndSaveUsersUseCase: FetchAndSaveUsersUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    // UI 상태를 나타내는 StateFlow
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // 1회성 이벤트를 위한 SharedFlow (Toast 등)
    private val _Ui_eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _Ui_eventFlow.asSharedFlow()

    init {
        // 앱 실행 시 데이터 로딩
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                fetchAndSaveUsersUseCase() // 서버에서 가져와 DB에 저장
                // DB에서 데이터를 관찰(Observe)
                getUsersUseCase().collect { users ->
                    _uiState.value = UiState.Success(users)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("데이터 로딩 실패: ${e.message}")
            }
        }
    }

    fun addUser(name: String, age: String) {
        viewModelScope.launch {
            try {
                val ageInt = age.toIntOrNull() ?: throw IllegalArgumentException("나이를 숫자로 입력해주세요.")
                if (name.isBlank()) throw IllegalArgumentException("이름을 입력해주세요.")

                addUserUseCase(User(name = name, age = ageInt))
                sendEvent(UiEvent.ShowToast("사용자 등록 완료"))
            } catch (e: Exception) {
                sendEvent(UiEvent.ShowToast("등록 실패: ${e.message}"))
            }
        }
    }

    fun updateUser(user: User, newName: String, newAge: String) {
        viewModelScope.launch {
            try {
                val ageInt = newAge.toIntOrNull() ?: throw IllegalArgumentException("나이를 숫자로 입력해주세요.")
                if (newName.isBlank()) throw IllegalArgumentException("이름을 입력해주세요.")

                updateUserUseCase(user.copy(name = newName, age = ageInt))
                sendEvent(UiEvent.ShowToast("사용자 수정 완료"))
            } catch (e: Exception) {
                sendEvent(UiEvent.ShowToast("수정 실패: ${e.message}"))
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            try {
                deleteUserUseCase(user)
                sendEvent(UiEvent.ShowToast("사용자 삭제 완료"))
            } catch (e: Exception) {
                sendEvent(UiEvent.ShowToast("삭제 실패: ${e.message}"))
            }
        }
    }

    private fun sendEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _Ui_eventFlow.emit(uiEvent)
        }
    }
}
