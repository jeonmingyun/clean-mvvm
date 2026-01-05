package com.min.cleanmvvm.data.datasource

import com.min.cleanmvvm.data.model.UserDto
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor() {

    suspend fun fetchUsers(): List<UserDto> {
        delay(1000) // mock network
        return listOf(
            UserDto(1, "Min"),
            UserDto(2, "ChatGPT")
        )
    }
}
