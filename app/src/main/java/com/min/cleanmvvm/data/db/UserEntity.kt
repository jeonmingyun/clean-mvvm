package com.min.cleanmvvm.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// Room 데이터베이스 테이블을 위한 Entity 클래스
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val age: Int
)