package com.example.tp3_petshop.repository

import com.example.tp3_petshop.models.Vehicle
import com.example.tp3_petshop.models.VehicleDetail
import com.example.tp3_petshop.models.VehicleRequest
import com.example.tp3_petshop.network.RetrofitInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepository @Inject constructor() {

    suspend fun createVehicle(request: VehicleRequest): Vehicle {
        return RetrofitInstance.vehicleService.createVehicle(request)
    }

    suspend fun getVehicleById(id: String): Vehicle {
        return RetrofitInstance.vehicleService.getVehicleById(id)
    }

    suspend fun getVehicleWithPartsById(id: String): VehicleDetail {
        return RetrofitInstance.vehicleService.getVehicleWithPartsById(id)
    }

    suspend fun getAll(): List<Vehicle> {
        return RetrofitInstance.vehicleService.getAll()
    }

}