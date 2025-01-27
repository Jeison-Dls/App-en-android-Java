package com.example.hospitalturnmanagement.ui.turnos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hospitalturnmanagement.data.repository.TurnosRepository

// Factory personalizada para crear instancias de TurnosViewModel
class TurnosViewModelFactory(private val repository: TurnosRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Verifica si la clase solicitada es TurnosViewModel o una subclase de esta
        if (modelClass.isAssignableFrom(TurnosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TurnosViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
