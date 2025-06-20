package com.example.tp3_petshop.network

import com.example.tp3_petshop.models.ChangeStatusRequest
import com.example.tp3_petshop.models.VehicleState
import com.example.tp3_petshop.models.VehicleStateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface VehicleStateService {
    @POST("/vehicle-state/create")
    suspend fun createState(@Body request: VehicleStateRequest): Response<VehicleState>

    @GET("/vehicle-state/get-all")
    suspend fun getAll(): Response<List<VehicleState>>

    @POST("/vehicle-state/change-state")
    suspend fun changeState(
        @Body request: ChangeStatusRequest
    ): Response<VehicleState>
}
