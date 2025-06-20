package com.example.tp3_petshop.models

data class Report(
    val additional_comments: String,
    val comparison_with_reference: String,
    val estimated_brand: String,
    val estimated_model: String,
    val id: String,
    val image_quality: String,
    val image_type: String,
    val is_same_unit_as_reference: Boolean,
    val is_vehicle_valid: Boolean,
    val part_damages: List<PartDamage>,
    val same_unit_confidence: Int,
    val total_vehicle_damage_percentage: String,
    val validation_reasons: String,
    val vehicle_state_id: String,
    val vehicle_type: String
)

data class PartDamage(
    val ai_report_id: String,
    val confidence_percentage: Int,
    val damage_description: String,
    val damage_type: String,
    val id: String,
    val part_name: String,
    val present_in_reference: Boolean,
    val severity: String
)
