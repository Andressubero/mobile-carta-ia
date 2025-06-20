package com.example.tp3_petshop.models

data class VehicleResponse(
    val products: List<Vehicle>
)

data class Vehicle(
    val brand: String,
    val id: String,
    val model: String,
    val plate: String,
    val vehicle_type_id: String,
    val year: Int
)
