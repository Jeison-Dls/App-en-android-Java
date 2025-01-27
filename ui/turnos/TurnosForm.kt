package com.example.hospitalturnmanagement.ui.turnos

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospitalturnmanagement.data.model.Doctor
import com.example.hospitalturnmanagement.data.model.Patient
import com.example.hospitalturnmanagement.data.model.Appointment
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.Search

// Formulario para crear o editar un turno (cita médica)
@Composable
fun TurnosForm(
    selectedDoctor: Doctor?, // Médico seleccionado
    selectedPatient: Patient?, // Paciente seleccionado
    onDoctorSearch: () -> Unit, // Callback para buscar médicos
    onPatientSearch: () -> Unit, // Callback para buscar pacientes
    onSubmit: (Appointment) -> Unit // Callback para guardar el turno
) {
    var appointmentStatus by remember { mutableStateOf("Pendiente") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Campo para seleccionar médico
        OutlinedTextField(
            value = selectedDoctor?.let { "${it.firstName} ${it.lastName}" } ?: "",
            onValueChange = {},
            label = { Text("Médico") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = onDoctorSearch) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar Médico")
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo para seleccionar paciente
        OutlinedTextField(
            value = selectedPatient?.let { "${it.firstName} ${it.lastName}" } ?: "",
            onValueChange = {},
            label = { Text("Paciente") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = onPatientSearch) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar Paciente")
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo para el estado del turno
        OutlinedTextField(
            value = appointmentStatus,
            onValueChange = { appointmentStatus = it },
            label = { Text("Estado del Turno") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para enviar
        Button(
            onClick = {
                if (selectedDoctor != null && selectedPatient != null) {
                    onSubmit(
                        Appointment(
                            id = null,
                            doctorId = selectedDoctor.id ?: 0,
                            patientId = selectedPatient.id ?: 0,
                            appointmentDate = "2025-01-01T12:00:00", // Temporal
                            status = "pendiente"
                        )
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = selectedDoctor != null && selectedPatient != null
        ) {
            Text("Guardar Turno")
        }
    }
}
