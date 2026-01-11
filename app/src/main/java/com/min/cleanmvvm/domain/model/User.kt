package com.min.cleanmvvm.domain.model

// Domain Layer에서 사용하는 핵심 비즈니스 모델
data class User(
    val id: Long = 0, // 0이면 신규 사용자
    val name: String,
    val age: Int
)