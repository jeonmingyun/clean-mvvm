package com.min.cleanmvvm.data.datasource

import com.min.cleanmvvm.data.api.UserApiService
import com.min.cleanmvvm.data.model.UserDto
import javax.inject.Inject

// 원격(서버) 데이터 소스를 담당
class UserRemoteDataSource @Inject constructor(private val apiService: UserApiService) {

    // Mock 데이터를 반환하는 함수
    suspend fun getUsers(): List<UserDto> {
        // 실제 통신 대신 Mock 데이터 생성
        return listOf(
            UserDto(1, "홍길동", 30),
            UserDto(2, "이순신", 45)
        )
        // 실제 API 호출 시
        // val response = apiService.getUsers()
        // if (response.isSuccessful) {
        //     return response.body() ?: emptyList()
        // } else {
        //     throw Exception("Failed to fetch users")
        // }
    }

    // 아래 함수들은 실제 서버와 통신한다고 가정 (성공/실패만 로그로 출력)
    suspend fun addUser(user: UserDto) {
        println("서버로 사용자 등록 요청: $user")
        // apiService.addUser(user)
    }

    suspend fun updateUser(user: UserDto) {
        println("서버로 사용자 수정 요청: $user")
        // apiService.updateUser(user.id, user)
    }

    suspend fun deleteUser(userId: Long) {
        println("서버로 사용자 삭제 요청: ID $userId")
        // apiService.deleteUser(userId)
    }
}