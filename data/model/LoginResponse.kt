package com.example.hospitalturnmanagement.data.model

// Clase de datos para representar la respuesta del servidor al iniciar sesión
data class LoginResponse(
    val token: String, // Token de autenticación generado por el servidor
    val message: String // Mensaje descriptivo de la operación (éxito, error, etc.)
)
