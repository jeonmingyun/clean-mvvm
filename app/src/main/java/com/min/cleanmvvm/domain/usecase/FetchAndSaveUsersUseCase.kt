package com.min.cleanmvvm.domain.usecase

import com.min.cleanmvvm.domain.repository.UserRepository
import javax.inject.Inject

// 서버에서 사용자를 가져와 DB에 저장하는 UseCase
class FetchAndSaveUsersUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.fetchUsersAndSaveToDb()
}