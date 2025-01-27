package com.example.hospitalturnmanagement.data.repository

import com.example.hospitalturnmanagement.data.api.ApiClient
import com.example.hospitalturnmanagement.data.model.Doctor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Repositorio para gestionar las operaciones relacionadas con doctores
class DoctorsRepository {
    // Cliente API configurado desde ApiClient
    private val api = ApiClient.apiService

    //Obtener Doctores
    suspend fun getDoctors(): List<Doctor> {
        return withContext(Dispatchers.IO) {
            api.getDoctors()
        }
    }

    //Nuevo Doctor
    suspend fun createDoctor(doctor: Doctor) {
        withContext(Dispatchers.IO) {
            api.createDoctor(doctor)
        }
    }

    //Actualizar Doctor
    suspend fun updateDoctor(id: Int, doctor: Doctor) {
        withContext(Dispatchers.IO) {
            api.updateDoctor(id, doctor)
        }
    }

    //Eliminar Doctor
    suspend fun deleteDoctor(id: Int) {
        withContext(Dispatchers.IO) {
            api.deleteDoctor(id)
        }
    }
}
