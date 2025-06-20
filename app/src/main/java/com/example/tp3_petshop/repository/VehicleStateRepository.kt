package com.example.tp3_petshop.repository

import com.example.tp3_petshop.models.ChangeStatusRequest
import com.example.tp3_petshop.models.VehicleState
import com.example.tp3_petshop.models.VehicleStateRequest
import com.example.tp3_petshop.network.RetrofitInstance
import retrofit2.Response
import javax.inject.Inject

class VehicleStateRepository @Inject constructor() {

    suspend fun createState(request: VehicleStateRequest): Response<VehicleState> {
        return RetrofitInstance.vehicleStateService.createState(request)
    }

    suspend fun getAll(): Response<List<VehicleState>> {
        return RetrofitInstance.vehicleStateService.getAll()
    }

    suspend fun changeStatus(request: ChangeStatusRequest): Response<VehicleState> {
        return RetrofitInstance.vehicleStateService.changeState(
            request
        )
    }

}