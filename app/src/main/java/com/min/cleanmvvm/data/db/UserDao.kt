package com.min.cleanmvvm.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// 데이터베이스에 접근하기 위한 DAO(Data Access Object)
@Dao
interface UserDao {
    // 모든 사용자 정보를 Flow 형태로 가져오기 (DB 변경 시 자동 업데이트)
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    // 사용자 추가 (중복 시 교체)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    // 여러 사용자 추가
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users: List<UserEntity>)

    // 사용자 정보 업데이트
    @Update
    suspend fun updateUser(user: UserEntity)

    // 사용자 삭제
    @Delete
    suspend fun deleteUser(user: UserEntity)
}