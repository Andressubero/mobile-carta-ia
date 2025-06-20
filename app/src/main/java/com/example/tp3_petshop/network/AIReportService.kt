package com.example.tp3_petshop.network

import com.example.tp3_petshop.models.Report
import retrofit2.http.GET
import retrofit2.http.Path

interface AIReportService {

    @GET("/report/get-detail/{id}")
    suspend fun getReport(@Path("id") id: String): Report

}