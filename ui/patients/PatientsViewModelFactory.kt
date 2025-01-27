package com.example.hospitalturnmanagement.ui.patients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hospitalturnmanagement.data.repository.PatientsRepository

// Factory personalizada para crear instancias de PatientsViewModel
class PatientsViewModelFactory(
    private val repository: PatientsRepository // Recibe el repositorio de pacientes como dependencia
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PatientsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PatientsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
