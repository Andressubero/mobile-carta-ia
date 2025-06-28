package com.example.tp3_petshop.models

data class VehicleState(
    val creation_date: String? = "No provisto",
    val declared_date: String? = "No provisto",
    val id: String? = "No provisto",
    val parts_state: List<PartState>? = emptyList(),
    val validation_reasons: String? = "No provisto",
    val validation_state: String? = "No provisto",
    val vehicle_brand: String? = "No provisto",
    val vehicle_id: String? = "No provisto",
    val vehicle_model: String? = "No provisto"
)

data class PartState(
    val creation_date: String? = "No provisto",
    val damages: List<Damage>? = emptyList(),
    val id: String? = "No provisto",
    val image: String? = "No provisto",
    val vehicle_part_id: String? = "No provisto",
    val vehicle_part_name: String? = "No provisto"
)

data class Damage(
    val damage_type: String? = "No provisto",
    val description: String? = "No provisto",
    val fixed: Boolean? = false,
    val id: String? = "No provisto"
)


