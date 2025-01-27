package com.example.hospitalturnmanagement.ui.turnos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hospitalturnmanagement.data.model.Appointment

// Composable que muestra una tabla con la lista de turnos (citas médicas)
@Composable
fun TurnosTable(
    appointments: List<Appointment>, // Lista de turnos a mostrar
    onDelete: (Int) -> Unit, // Callback para manejar la eliminación de un turno
    onEdit: (Appointment) -> Unit // Callback para manejar la edición de un turno
) {
    LazyColumn(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        items(appointments) { appointment ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Información del turno
                    Column(modifier = Modifier.weight(2f)) {
                        Text(
                            text = "Paciente: ${appointment.patientName ?: "Desconocido"}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Doctor: ${appointment.doctorName ?: "Desconocido"}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Estado: ${appointment.status ?: "No especificado"}",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        )
                    }

                    // Botones de acción
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { onEdit(appointment) }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                        }
                        IconButton(onClick = { appointment.id?.let { onDelete(it) } }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            }
        }
    }
}
