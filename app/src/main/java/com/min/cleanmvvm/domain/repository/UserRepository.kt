package com.min.cleanmvvm.domain.repository

import com.min.cleanmvvm.domain.model.User
import kotlinx.coroutines.flow.Flow

// Domain Layer에서 정의하는 Repository 인터페이스 (Data Layer에서 구현)
interface UserRepository {
    fun getAllUsersFromDb(): Flow<List<User>>
    suspend fun fetchUsersAndSaveToDb()
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}