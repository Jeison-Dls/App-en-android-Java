package com.example.hospitalturnmanagement.data.api

import com.example.hospitalturnmanagement.data.model.LoginRequest
import com.example.hospitalturnmanagement.data.model.LoginResponse
import com.example.hospitalturnmanagement.data.model.*
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET


interface ApiService {
    // Autenticación
    // Envía una solicitud POST a la ruta "/login" con un cuerpo JSON para autenticar al usuario.
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // Gestión de pacientes
    // Recupera la lista de pacientes desde la ruta "/patients".
    @GET("patients")
    suspend fun getPatients(): List<Patient>

    // Crea un nuevo paciente enviando un objeto `Patient` al endpoint "/patients".
    @POST("patients")
    suspend fun createPatient(@Body patient: Patient): CreatePatientResponse // Cambiado aquí

    // Actualiza los datos de un paciente específico identificado por `id`.
    @PUT("patients/{id}")
    suspend fun updatePatient(@Path("id") id: Int, @Body patient: Patient)

    // Elimina un paciente específico identificado por `id`.
    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id: Int)

    // Gestión de doctores
    // Recupera la lista de doctores desde la ruta "/doctors".
    @GET("doctors")
    suspend fun getDoctors(): List<Doctor>

    // Crea un nuevo doctor enviando un objeto `Doctor` al endpoint "/doctors".
    @POST("doctors")
    suspend fun createDoctor(@Body doctor: Doctor)

    // Actualiza los datos de un doctor específico identificado por `id`.
    @PUT("doctors/{id}")
    suspend fun updateDoctor(@Path("id") id: Int, @Body doctor: Doctor)

    // Elimina un doctor específico identificado por `id".
    @DELETE("doctors/{id}")
    suspend fun deleteDoctor(@Path("id") id: Int)

    // Gestión de turnos (citas)
    // Recupera la lista de turnos desde la ruta "/appointments".
    @GET("appointments")
    suspend fun getAppointments(): List<Appointment>

    // Crea un nuevo turno enviando un objeto `Appointment` al endpoint "/appointments".
    @POST("appointments")
    suspend fun createAppointment(@Body appointment: Appointment): Response<Appointment>

    // Actualizar un turno
    // Actualiza el estado de un turno identificado por `id`.
    @PUT("appointments/{id}")
    suspend fun updateAppointment(
        @Path("id") id: Int,
        @Body status: Map<String, String> // Ejemplo: {"status": "completado"}
    ): Response<Unit>

    // Eliminar un turno
    // Elimina un turno específico identificado por `id".
    @DELETE("appointments/{id}")
    suspend fun deleteAppointment(@Path("id") id: Int): Response<Unit>



}
