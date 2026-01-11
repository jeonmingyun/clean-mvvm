package com.min.cleanmvvm.domain.usecase

import com.min.cleanmvvm.domain.model.User
import com.min.cleanmvvm.domain.repository.UserRepository
import javax.inject.Inject

// 사용자를 수정하는 UseCase
class UpdateUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: User) = repository.updateUser(user)
}