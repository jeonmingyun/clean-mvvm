package com.min.cleanmvvm.domain.usecase

import com.min.cleanmvvm.domain.model.User
import com.min.cleanmvvm.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// DB에서 사용자 목록을 가져오는 UseCase
class GetUsersUseCase @Inject constructor(private val repository: UserRepository) {
    operator fun invoke(): Flow<List<User>> = repository.getAllUsersFromDb()
}