package com.min.cleanmvvm.data.model

import com.min.cleanmvvm.domain.model.User

data class UserDto(
    val id: Int,
    val name: String
) {
    fun toDomain() = User(id, name)
}
