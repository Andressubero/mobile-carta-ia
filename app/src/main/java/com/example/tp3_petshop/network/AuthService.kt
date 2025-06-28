package com.example.tp3_petshop.network

import com.example.tp3_petshop.models.LoginRequest
import com.example.tp3_petshop.models.LoginResponse
import com.example.tp3_petshop.models.Register
import com.example.tp3_petshop.models.RegisterResponse
import com.example.tp3_petshop.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("user/create")
    suspend fun register(@Body request: Register): Response<RegisterResponse>

    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("user/me")
    suspend fun getMe(): Response<User>
}