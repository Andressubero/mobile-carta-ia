package com.example.tp3_petshop.network

import com.example.tp3_petshop.models.Login
import com.example.tp3_petshop.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: Login): LoginResponse
}