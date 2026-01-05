package com.min.cleanmvvm.domain.repository

import com.min.cleanmvvm.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}
