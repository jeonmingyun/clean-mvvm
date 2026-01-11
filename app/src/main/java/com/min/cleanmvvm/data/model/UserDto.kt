package com.min.cleanmvvm.data.model

// 서버 API 응답을 위한 데이터 클래스 (Data Transfer Object)
data class UserDto(
    val id: Long,
    val name: String,
    val age: Int
)
