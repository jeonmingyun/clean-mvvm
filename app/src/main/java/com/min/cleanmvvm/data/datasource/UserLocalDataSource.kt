package com.min.cleanmvvm.data.datasource

import com.min.cleanmvvm.data.db.UserDao
import com.min.cleanmvvm.data.db.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// 로컬(DB) 데이터 소스를 담당
class UserLocalDataSource @Inject constructor(private val userDao: UserDao) {
    fun getAllUsers(): Flow<List<UserEntity>> = userDao.getAllUsers()
    suspend fun insertAllUsers(users: List<UserEntity>) = userDao.insertAllUsers(users)
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    suspend fun updateUser(user: UserEntity) = userDao.updateUser(user)
    suspend fun deleteUser(user: UserEntity) = userDao.deleteUser(user)
}