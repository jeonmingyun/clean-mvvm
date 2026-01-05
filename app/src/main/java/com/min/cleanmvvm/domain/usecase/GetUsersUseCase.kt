package com.min.cleanmvvm.domain.usecase

import com.min.cleanmvvm.domain.model.User
import com.min.cleanmvvm.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<User> =
        repository.getUsers()
}
