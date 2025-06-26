package com.example.tp3_petshop.network

import com.example.tp3_petshop.models.Vehicle
import com.example.tp3_petshop.models.VehicleDetail
import com.example.tp3_petshop.models.VehicleRequest
import com.example.tp3_petshop.models.VehicleResponse
import com.example.tp3_petshop.models.VehicleTypeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VehicleService {
    @POST("/vehicle/create")
    suspend fun createVehicle(@Body request: VehicleRequest): VehicleResponse

    @GET("/vehicle/{id}")
    suspend fun getVehicleById(@Path("id") id: String): Vehicle

    @GET("/vehicle-type/findAll")
    suspend fun getVehicleTypes(): VehicleTypeResponse

    @GET("/vehicle/vehicle-with-parts/{id}")
    suspend fun getVehicleWithPartsById(@Path("id") id: String): VehicleDetail

    @GET("/vehicle/myVehicles")
    suspend fun getAll(): List<Vehicle>
}