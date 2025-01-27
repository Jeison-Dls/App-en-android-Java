package com.example.hospitalturnmanagement.ui.doctors

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospitalturnmanagement.data.model.Doctor

// Composable que representa un formulario para crear o actualizar un doctor
@Composable
fun DoctorForm(
    onSubmit: (Doctor) -> Unit, // Callback que se ejecuta al guardar/actualizar el doctor
    initialDoctor: Doctor? = null // Doctor inicial para edición, nulo si es uno nuevo
) {
    // Estados que se actualizan automáticamente cuando cambia initialDoctor
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var availabilityRange by remember { mutableStateOf("") }
    var specialty by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf(false) }

    // Actualizar valores cuando cambia initialDoctor
    LaunchedEffect(initialDoctor) {
        firstName = initialDoctor?.firstName ?: ""
        lastName = initialDoctor?.lastName ?: ""
        email = initialDoctor?.email ?: ""
        phone = initialDoctor?.phone ?: ""
        availabilityRange = initialDoctor?.availabilityRange ?: ""
        specialty = initialDoctor?.specialty ?: ""
        experience = initialDoctor?.experience ?: false
    }

    // Validar que todos los campos requeridos estén llenos
    val isFormValid = firstName.isNotBlank() &&
            lastName.isNotBlank() &&
            email.isNotBlank() &&
            phone.isNotBlank() &&
            availabilityRange.isNotBlank() &&
            specialty.isNotBlank()

    // Diseño del formulario
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        // Campos de texto para cada dato del doctor
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Apellido") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = availabilityRange,
            onValueChange = { availabilityRange = it },
            label = { Text("Horario Disponible (HH:MM-HH:MM)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = specialty,
            onValueChange = { specialty = it },
            label = { Text("Especialidad") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(checked = experience, onCheckedChange = { experience = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text("¿Tiene experiencia?")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar/actualizar los datos
        Button(
            onClick = {
                val doctor = Doctor(
                    id = initialDoctor?.id, // Mantiene el ID si es una actualización
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    phone = phone,
                    availabilityRange = availabilityRange,
                    specialty = specialty,
                    experience = experience,
                    role = initialDoctor?.role ?: "" // Esto lo calcula el backend
                )
                onSubmit(doctor) // Llama al callback con el doctor creado/actualizado

                // Limpia los campos después de guardar
                firstName = ""
                lastName = ""
                email = ""
                phone = ""
                availabilityRange = ""
                specialty = ""
                experience = false
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid // Habilitar/deshabilitar el botón
        ) {
            Text(if (initialDoctor == null) "Guardar" else "Actualizar")
        }
    }
}
