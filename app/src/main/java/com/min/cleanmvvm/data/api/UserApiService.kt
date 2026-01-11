package com.min.cleanmvvm.data.api

import com.min.cleanmvvm.data.model.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Retrofit을 이용한 API 서비스 인터페이스
interface UserApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>

    @POST("users")
    suspend fun addUser(@Body user: UserDto): Response<Unit>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Long, @Body user: UserDto): Response<Unit>

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Long): Response<Unit>
}