package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.ChangeStatusRequest
import com.example.tp3_petshop.models.Report
import com.example.tp3_petshop.models.VehicleState
import com.example.tp3_petshop.models.VehicleStateRequest
import com.example.tp3_petshop.repository.ReportRepository
import com.example.tp3_petshop.repository.VehicleStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReportViewModel @Inject constructor(
    private val repository: ReportRepository
) : ViewModel() {

    private val _report = MutableStateFlow<Report?>(null)
    val report: StateFlow<Report?> = _report


    private val _cartError = MutableStateFlow<String?>(null)
    val cartError: StateFlow<String?> = _cartError

    fun getById(id: String) {
        viewModelScope.launch {
            try {
                _report.value = repository.getById(id)
            } catch (e: Exception) {
                _cartError.value = "Exception: ${e.message}"
            }
        }
    }
}
