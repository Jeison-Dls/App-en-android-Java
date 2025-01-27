package com.example.hospitalturnmanagement.ui.turnos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospitalturnmanagement.data.model.Patient

// Composable que muestra un modal para seleccionar un paciente
@Composable
fun PacientesModal(
    patients: List<Patient>, // Lista de pacientes disponibles
    onPatientSelect: (Patient) -> Unit, // Callback que se ejecuta al seleccionar un paciente
    onDismiss: () -> Unit // Callback que se ejecuta al cerrar el modal
) {
    // Modal de alerta para mostrar la lista de pacientes
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Seleccionar Paciente") },
        text = {
            LazyColumn {
                items(patients) { patient ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "${patient.firstName} ${patient.lastName}",
                                style = MaterialTheme.typography.body1
                            )
                            Text(
                                text = "Prioridad: ${patient.tipoPrioridad}",
                                style = MaterialTheme.typography.body2
                            )
                        }
                        Button(onClick = { onPatientSelect(patient) }) {
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
