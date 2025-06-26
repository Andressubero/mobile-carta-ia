package com.example.tp3_petshop.models

data class VehicleResponse(
    val message: String,
    val success: Boolean,
    val vehicle: VehicleIdWrapper?
)
data class VehicleIdWrapper(
    val id: String
)
data class Vehicle(
    val brand: String,
    val id: String,
    val model: String,
    val plate: String,
    val vehicleTypeId: String,
    val year: Int
)

data class VehicleRequest(
    val vehicleTypeId: String,
    val model: String,
    val brand: String,
    val year: Int,
    val plate: String
)

data class VehicleTypeResponse(
    val vehicle_types: List<VehicleType>
)

data class VehicleType(
    val id: String,
    val name: String
)