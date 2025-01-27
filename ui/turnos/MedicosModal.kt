package com.example.hospitalturnmanagement.ui.turnos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospitalturnmanagement.data.model.Doctor

// Composable que muestra un modal para seleccionar un médico
@Composable
fun MedicosModal(
    doctors: List<Doctor>, // Lista de médicos disponibles
    onDoctorSelect: (Doctor) -> Unit, // Callback que se ejecuta al seleccionar un médico
    onDismiss: () -> Unit // Callback que se ejecuta al cerrar el modal
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Seleccionar Médico") },
        text = {
            LazyColumn {
                items(doctors) { doctor ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = "${doctor.firstName} ${doctor.lastName}", style = MaterialTheme.typography.body1)
                            Text(text = "Especialidad: ${doctor.specialty}", style = MaterialTheme.typography.body2)
                        }
                        Button(onClick = { onDoctorSelect(doctor) }) {
                            Text("Seleccionar")
                        }
                    }
                }
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss) {
                    Text("Cerrar")
                }
            }
        }
    )
}
