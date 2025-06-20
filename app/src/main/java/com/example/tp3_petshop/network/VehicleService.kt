package com.example.tp3_petshop.network

import com.example.tp3_petshop.models.Vehicle
import com.example.tp3_petshop.models.VehicleDetail
import com.example.tp3_petshop.models.VehicleRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleService {
    @GET("/vehicle/create")
    suspend fun createVehicle(@Body request: VehicleRequest): Vehicle

    @GET("/vehicle/{id}")
    suspend fun getVehicleById(@Path("id") id: String): Vehicle

    @GET("/vehicle/vehicle-with-parts/{id}")
    suspend fun getVehicleWithPartsById(@Path("id") id: String): VehicleDetail

    @GET("/vehicle/myVehicles")
    suspend fun getAll(): List<Vehicle>
}