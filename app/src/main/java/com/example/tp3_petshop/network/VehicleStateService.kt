package com.example.tp3_petshop.network

import com.example.tp3_petshop.models.ChangeStatusRequest
import com.example.tp3_petshop.models.IsFirstStateResponse
import com.example.tp3_petshop.models.VehicleState
import com.example.tp3_petshop.models.VehicleStateRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Streaming

interface VehicleStateService {

    @GET("/vehicle-state/get-all")
    @Streaming
    suspend fun getAll(): Response<ResponseBody>

    @GET("/vehicle-state/is-first-state/{id}")
    suspend fun isFirstState(@Path("id") id: String): Response<IsFirstStateResponse>

    @POST("/vehicle-state/change-state")
    suspend fun changeState(
        @Body request: ChangeStatusRequest
    ): Response<VehicleState>

    @Multipart
    @POST("vehicle-state/create")
    suspend fun createVehicleState(
        @Part("vehicle_id") vehicleId: RequestBody,
        @Part("date") date: RequestBody,
        @Part("brand") brand: RequestBody,
        @Part("model") model: RequestBody,
        @Part("states") states: RequestBody,
        @Part lateralRight: MultipartBody.Part?,
        @Part lateralLeft: MultipartBody.Part?,
        @Part front: MultipartBody.Part?,
        @Part back: MultipartBody.Part?,
        @Part top: MultipartBody.Part?
    ): Response<ResponseBody>
}
