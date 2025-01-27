package com.example.hospitalturnmanagement.ui.patients

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospitalturnmanagement.data.model.Patient
import androidx.compose.material.Text



import java.time.LocalDate
import java.time.Period

import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PatientForm(
    initialPatient: Patient? = null,
    onSubmit: (Patient) -> Unit
) {
    var firstName by remember(initialPatient) { mutableStateOf(initialPatient?.firstName ?: "") }
    var lastName by remember(initialPatient) { mutableStateOf(initialPatient?.lastName ?: "") }
    var dateOfBirth by remember(initialPatient) { mutableStateOf(initialPatient?.dateOfBirth ?: "") }
    var gender by remember(initialPatient) { mutableStateOf(initialPatient?.gender ?: "") }
    var phone by remember(initialPatient) { mutableStateOf(initialPatient?.phone ?: "") }
    var email by remember(initialPatient) { mutableStateOf(initialPatient?.email ?: "") }

    // Validar que todos los campos estén completos
    val isFormValid =
        firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                dateOfBirth.isNotBlank() &&
                gender.isNotBlank() &&
                phone.isNotBlank() &&
                email.isNotBlank()


    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
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
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text("Fecha de Nacimiento (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Género") },
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
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Cálculo de edad usando Calendar y SimpleDateFormat
                val age = try {
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    val birthDate = sdf.parse(dateOfBirth)
                    val birthCalendar = Calendar.getInstance().apply { time = birthDate }
                    val today = Calendar.getInstance()

                    var calculatedAge = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)

                    if (today.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
                        calculatedAge--
                    }
                    calculatedAge
                } catch (e: Exception) {
                    null
                }

                val patient = Patient(
                    id = initialPatient?.id,
                    firstName = firstName,
                    lastName = lastName,
                    dateOfBirth = dateOfBirth,
                    gender = gender,
                    phone = phone,
                    email = email,
                    age = age, // Incluye la edad calculada
                    medicoAsignado = initialPatient?.medicoAsignado ?: "Sin asignar",
                    tipoPrioridad = initialPatient?.tipoPrioridad ?: "Baja"
                )
                onSubmit(patient)

                // Limpia los campos después de guardar
                firstName = ""
                lastName = ""
                dateOfBirth = ""
                gender = ""
                phone = ""
                email = ""
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid // Habilitar o deshabilitar el botón
        ) {
            Text(if (initialPatient != null) "Actualizar" else "Guardar")
        }
    }
}
