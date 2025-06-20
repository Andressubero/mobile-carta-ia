package com.example.tp3_petshop.models

data class VehicleState(
    val creation_date: String,
    val declared_date: String,
    val id: String,
    val parts_state: List<PartState>,
    val validation_reasons: String,
    val validation_state: String,
    val vehicle_brand: String,
    val vehicle_id: String,
    val vehicle_model: String
)

data class PartState(
    val creation_date: String,
    val damages: List<Damage>,
    val id: String,
    val image: String,
    val vehicle_part_id: String,
    val vehicle_part_name: String
)

data class Damage(
    val damage_type: String,
    val description: String,
    val fixed: Boolean,
    val id: String
)

