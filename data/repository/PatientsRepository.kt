package com.example.hospitalturnmanagement.data.repository

import android.util.Log
import com.example.hospitalturnmanagement.data.api.ApiClient
import com.example.hospitalturnmanagement.data.model.Patient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

// Repositorio para manejar las operaciones relacionadas con pacientes
class PatientsRepository {
    // Cliente API configurado desde ApiClient
    private val api = ApiClient.apiService

    // lista de pacientes desde el backend
    suspend fun getPatients(): List<Patient> {
        return withContext(Dispatchers.IO) {
            api.getPatients()
        }
    }
    // Método para crear un nuevo paciente
    suspend fun createPatient(patient: Patient): Int? {
        return try {
            // Intenta enviar una solicitud POST para crear un paciente
            val response = api.createPatient(patient)
            Log.d("Response", "Paciente creado: $response")
            response.id // Devuelve el ID del paciente creado
        } catch (e: HttpException) {
            // Registra el cuerpo de la respuesta de error
            Log.e("HTTP Error", "Error ${e.code()}: ${e.response()?.errorBody()?.string()}")
            null
        } catch (e: Exception) {
            // Maneja cualquier otro tipo de error
            Log.e("Error", "Error: ${e.message}")
            null
        }
    }



    // actualizar la información de un paciente existente
    suspend fun updatePatient(id: Int, patient: Patient) {
        withContext(Dispatchers.IO) {
            api.updatePatient(id, patient)
        }
    }

    // eliminar un paciente por su ID
    suspend fun deletePatient(id: Int) {
        withContext(Dispatchers.IO) {
            api.deletePatient(id)
        }
    }
}
