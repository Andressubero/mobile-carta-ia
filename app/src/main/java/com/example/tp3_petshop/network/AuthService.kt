package com.example.tp3_petshop.network

import com.example.tp3_petshop.models.Login
import com.example.tp3_petshop.models.LoginResponse
import com.example.tp3_petshop.models.LogoutResponse
import com.example.tp3_petshop.models.Register
import com.example.tp3_petshop.models.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("user/create")
    suspend fun register(@Body request: Register): RegisterResponse

    @POST("user/login")
    suspend fun login(@Body request: Login): LoginResponse

    @GET("user/me")
    suspend fun getMe(@Body request: Login): LoginResponse

    @POST("user/logout")
    suspend fun logout(): LogoutResponse
}