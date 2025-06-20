package com.example.tp3_petshop.data

import com.example.tp3_petshop.models.PartPosition

val sedanPoints = listOf(
    "Techo" to Triple(43.1f, 48.7f, "TOP"),
    "Parabrisas" to Triple(64.6f, 49.2f, "FRONT"),
    "Luneta Trasera" to Triple(23.9f, 49.2f, "BACK"),
    "Baúl" to Triple(13.6f, 49.4f, "BACK"),
    "Paragolpes trasero" to Triple(6.0f, 49.1f, "BACK"),
    "Capó" to Triple(80.6f, 49.2f, "FRONT"),
    "Paragolpes delantero" to Triple(92.6f, 49.2f, "FRONT"),
    "Rueda delantera derecha" to Triple(80.2f, 86.4f, "LATERAL_RIGHT"),
    "Rueda trasera derecha" to Triple(21.7f, 86.2f, "LATERAL_RIGHT"),
    "Ventana delantera derecha" to Triple(52.9f, 68.4f, "LATERAL_RIGHT"),
    "Ventana trasera derecha" to Triple(41.7f, 68.7f, "LATERAL_RIGHT"),
    "Puerta delantera derecha" to Triple(53.0f, 80.7f, "LATERAL_RIGHT"),
    "Puerta trasera derecha" to Triple(41.3f, 80.7f, "LATERAL_RIGHT"),
    "Guarda fango delantero derecho" to Triple(75.0f, 74.9f, "LATERAL_RIGHT"),
    "Guarda fango trasero derecho" to Triple(25.8f, 71.2f, "LATERAL_RIGHT"),
    "Luz delantera derecha" to Triple(86.0f, 70.7f, "LATERAL_RIGHT"),
    "Luz trasera derecha" to Triple(9.7f, 75.7f, "LATERAL_RIGHT"),
    "Retrovisor derecho" to Triple(64.6f, 68.4f, "LATERAL_RIGHT"),
    "Rueda delantera izquierda" to Triple(80.2f, 11.4f, "LATERAL_LEFT"),
    "Rueda trasera izquierda" to Triple(21.7f, 12.2f, "LATERAL_LEFT"),
    "Ventana delantera izquierda" to Triple(52.9f, 29.7f, "LATERAL_LEFT"),
    "Ventana trasera izquierda" to Triple(41.7f, 29.7f, "LATERAL_LEFT"),
    "Puerta delantera izquierda" to Triple(53.0f, 16.7f, "LATERAL_LEFT"),
    "Puerta trasera izquierda" to Triple(41.3f, 17.2f, "LATERAL_LEFT"),
    "Guarda fango delantero izquierdo" to Triple(75.0f, 23.2f, "LATERAL_LEFT"),
    "Guarda fango trasero izquierdo" to Triple(25.8f, 26.7f, "LATERAL_LEFT"),
    "Luz delantera izquierda" to Triple(86.0f, 26.9f, "LATERAL_LEFT"),
    "Luz trasera izquierda" to Triple(9.7f, 22.2f, "LATERAL_LEFT"),
    "Retrovisor izquierdo" to Triple(64.6f, 30.2f, "LATERAL_LEFT")
).mapIndexed { index, (name, values) ->
    PartPosition(
        name = name,
        topPercent = values.first,
        leftPercent = values.second,
        side = values.third
    )
}
