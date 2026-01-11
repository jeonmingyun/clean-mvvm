package com.min.cleanmvvm.data.repository

import com.min.cleanmvvm.data.datasource.UserLocalDataSource
import com.min.cleanmvvm.data.datasource.UserRemoteDataSource
import com.min.cleanmvvm.data.db.UserEntity
import com.min.cleanmvvm.data.model.UserDto
import com.min.cleanmvvm.domain.model.User
import com.min.cleanmvvm.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Data Layer의 Repository 구현체
class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    // Domain 모델을 Data 모델(Entity)로 변환
    private fun User.toEntity() = UserEntity(id = this.id, name = this.name, age = this.age)
    private fun UserEntity.toDomain() =
        com.min.cleanmvvm.domain.model.User(id = this.id, name = this.name, age = this.age)

    private fun UserDto.toEntity() = UserEntity(name = this.name, age = this.age) // id는 DB에서 자동 생성

    override fun getAllUsersFromDb(): Flow<List<User>> {
        // DB의 Entity를 Domain 모델로 변환하여 반환
        return localDataSource.getAllUsers().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun fetchUsersAndSaveToDb() {
        // 서버에서 데이터를 가져와 DB에 저장
        val usersFromApi = remoteDataSource.getUsers()
        localDataSource.insertAllUsers(usersFromApi.map { it.toEntity() })
    }

    override suspend fun addUser(user: User) {
        // DB에 먼저 저장 후 서버에 전송
        localDataSource.insertUser(user.toEntity())
        remoteDataSource.addUser(UserDto(user.id, user.name, user.age)) // 실제로는 id가 동기화되어야 함
    }

    override suspend fun updateUser(user: User) {
        // DB에 먼저 수정 후 서버에 전송
        localDataSource.updateUser(user.toEntity())
        remoteDataSource.updateUser(UserDto(user.id, user.name, user.age))
    }

    override suspend fun deleteUser(user: User) {
        // DB에 먼저 삭제 후 서버에 전송
        localDataSource.deleteUser(user.toEntity())
        remoteDataSource.deleteUser(user.id)
    }
}