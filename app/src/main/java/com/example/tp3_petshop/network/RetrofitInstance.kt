package com.example.tp3_petshop.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://10.0.2.2:5000"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .cookieJar(CookieManager()) // 🔁 Esta instancia se comparte
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // 🔁 Todos usan este mismo cliente
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