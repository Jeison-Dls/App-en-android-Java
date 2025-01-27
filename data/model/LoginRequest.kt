package com.example.hospitalturnmanagement.data.model

// Clase de datos para representar la solicitud de inicio de sesión
data class LoginRequest(
    val username: String, // Nombre de usuario proporcionado por el cliente
    val password: String // Contraseña correspondiente
)
