package com.example.tp3_petshop.repository

import com.example.tp3_petshop.models.Report
import com.example.tp3_petshop.models.Vehicle
import com.example.tp3_petshop.models.VehicleDetail
import com.example.tp3_petshop.models.VehicleRequest
import com.example.tp3_petshop.network.RetrofitInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportRepository @Inject constructor() {

    suspend fun getById(id: String): Report {
        return RetrofitInstance.reportService.getReport(id)
    }

}