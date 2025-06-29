package com.example.tp3_petshop.repository

import com.example.tp3_petshop.models.ChangePasswordRequest
import com.example.tp3_petshop.models.LoginRequest
import com.example.tp3_petshop.models.LoginResponse
import com.example.tp3_petshop.models.Register
import com.example.tp3_petshop.models.RegisterResponse
import com.example.tp3_petshop.models.Report
import com.example.tp3_petshop.models.User
import com.example.tp3_petshop.models.VehicleState
import com.example.tp3_petshop.network.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor() {

    suspend fun getMe(): Response<User> {
        return RetrofitInstance.authService.getMe()
    }
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return RetrofitInstance.authService.login(request)
    }
    suspend fun register(request: Register): Response<RegisterResponse> {
        return RetrofitInstance.authService.register(request)
    }

    suspend fun changePassword(password: ChangePasswordRequest): Response<String> {
        return RetrofitInstance.authService.changePassword(password)
    }

    suspend fun logout(): Response<LoginResponse> {
        return RetrofitInstance.authService.logout()
    }

}