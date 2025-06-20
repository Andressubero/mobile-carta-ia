package com.example.tp3_petshop.models

data class VehicleDetail(
    val borrado: Boolean,
    val brand: String,
    val id: String,
    val model: String,
    val parts: List<VehiclePart>,
    val plate: String,
    val type: String,
    val user_id: String,
    val vehicle_type_id: String,
    val year: Int
)

data class VehiclePart(
    val id: String,
    val name: String
)