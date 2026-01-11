package com.min.cleanmvvm.domain.usecase

import com.min.cleanmvvm.domain.model.User
import com.min.cleanmvvm.domain.repository.UserRepository
import javax.inject.Inject

// 사용자를 삭제하는 UseCase
class DeleteUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: User) = repository.deleteUser(user)
}