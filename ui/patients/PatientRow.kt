package com.example.hospitalturnmanagement.ui.patients

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hospitalturnmanagement.data.model.Patient

@Composable
fun PatientRow(
    patient: Patient, // Objeto del paciente que contiene la información a mostrar
    onDelete: (Int) -> Unit, // Callback para manejar la acción de eliminar un paciente
    onEdit: (Patient) -> Unit // Callback para manejar la acción de editar un paciente
) {
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
            // Información del paciente
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = "Nombre: ${patient.firstName ?: "Sin nombre"} ${patient.lastName ?: "Sin apellido"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Edad: ${patient.age ?: "N/A"}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Teléfono: ${patient.phone ?: "N/A"}",
                    fontSize = 14.sp
                )
            }

            // Botones de acción
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { onEdit(patient) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = { patient.id?.let { onDelete(it) } }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}
