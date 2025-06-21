package com.example.tp3_petshop.models

import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap
import com.google.gson.annotations.SerializedName

data class PartPosition(
    val name: String,
    val topPercent: Float,  // ejemplo: 43.1f
    val leftPercent: Float, // ejemplo: 48.7f
    val side: String
)

enum class DamageType { ABOLLADURA, RAYON, OTRO, SIN_DANO }

data class DamagePoint(
    @SerializedName("damage_type") val damageType: DamageType,
    val description: String
)

data class EstadoParte(
    val name: String,
    @SerializedName("part_id") val partId: String,
    val damages: List<DamagePoint>
)


data class VehicleImage(
    val file: Uri,
    val preview: ImageBitmap
)



