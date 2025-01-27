package com.example.hospitalturnmanagement.ui.doctors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hospitalturnmanagement.data.repository.DoctorsRepository

// Factoría personalizada para crear instancias de DoctorsViewModel
class DoctorsViewModelFactory(
    private val repository: DoctorsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DoctorsViewModel::class.java)) {
            return DoctorsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
