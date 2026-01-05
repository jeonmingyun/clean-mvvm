package com.min.cleanmvvm.data.repository

import com.min.cleanmvvm.data.datasource.UserRemoteDataSource
import com.min.cleanmvvm.domain.model.User
import com.min.cleanmvvm.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remote: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUsers(): List<User> =
        remote.fetchUsers().map { it.toDomain() }
}
