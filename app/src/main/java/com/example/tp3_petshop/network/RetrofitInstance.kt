package com.example.tp3_petshop.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private const val BASE_URL = "http://10.0.2.2:5000"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .cookieJar(CookieManager()) // 🔁 Esta instancia se comparte
        .connectTimeout(60, TimeUnit.SECONDS) // ⏱ Tiempo para conectar
        .readTimeout(60, TimeUnit.SECONDS)    // ⏱ Tiempo para leer respuesta
        .writeTimeout(60, TimeUnit.SECONDS)   // ⏱ Tiempo para escribir request
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val vehicleService: VehicleService by lazy {
        retrofit.create(VehicleService::class.java)
    }

    val vehicleStateService: VehicleStateService by lazy {
        retrofit.create(VehicleStateService::class.java)
    }

    val reportService: AIReportService by lazy {
        retrofit.create(AIReportService::class.java)
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
}
