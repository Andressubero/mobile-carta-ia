package com.example.tp3_petshop.repository

import android.content.Context
import android.net.Uri
import com.example.tp3_petshop.models.ChangeStatusRequest
import com.example.tp3_petshop.models.EstadoParte
import com.example.tp3_petshop.models.IsFirstStateResponse
import com.example.tp3_petshop.models.VehicleImage
import com.example.tp3_petshop.models.VehicleState
import com.example.tp3_petshop.models.VehicleStateRequest
import com.example.tp3_petshop.network.RetrofitInstance
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import com.example.tp3_petshop.network.VehicleStateService


class VehicleStateRepository @Inject constructor(
) {

    suspend fun create(
        context: Context,
        vehicleId: String,
        date: String,
        brand: String,
        model: String,
        estadoPartes: List<EstadoParte>,
        images: Map<String, VehicleImage>,
    ): Response<ResponseBody> {
        val gson = Gson()

        val json = gson.toJson(estadoPartes)

        // Convertir datos simples
        fun String.toBody() = toRequestBody("text/plain".toMediaType())

        val formatter = DateTimeFormatter.ISO_DATE
        val parsedDate = LocalDate.parse(date).format(formatter)

        val sideToField = mapOf(
            "LATERAL_RIGHT" to "lateral_right",
            "LATERAL_LEFT" to "lateral_left",
            "FRONT" to "front",
            "BACK" to "back",
            "TOP" to "top"
        )


        fun imagePart(key: String): MultipartBody.Part? {
            val vehicleImage = images[key] ?: return null
            val fieldName = sideToField[key] ?: return null

            val inputStream = context.contentResolver.openInputStream(vehicleImage.file) ?: return null
            val bytes = inputStream.readBytes()
            val requestFile = bytes.toRequestBody("image/jpeg".toMediaTypeOrNull())
            return MultipartBody.Part.createFormData(fieldName, "$fieldName.jpg", requestFile)
        }
        return RetrofitInstance.vehicleStateService.createVehicleState(
            vehicleId = vehicleId.toBody(),
            date = parsedDate.toBody(),
            brand = brand.toBody(),
            model = model.toBody(),
            states = json.toBody(),
            lateralRight = imagePart("LATERAL_RIGHT"),
            lateralLeft = imagePart("LATERAL_LEFT"),
            front = imagePart("FRONT"),
            back = imagePart("BACK"),
            top = imagePart("TOP")
        )
    }


    suspend fun getAll(): Response<List<VehicleState>> {
        return RetrofitInstance.vehicleStateService.getAll()
    }

    suspend fun changeStatus(request: ChangeStatusRequest): Response<VehicleState> {
        return RetrofitInstance.vehicleStateService.changeState(request)
    }

    suspend fun isFirstState(id: String): Response<IsFirstStateResponse> {
        return RetrofitInstance.vehicleStateService.isFirstState(id)
    }
    suspend fun getById(id: String): Response<VehicleState> {
        return RetrofitInstance.vehicleStateService.getById(id)
    }

}