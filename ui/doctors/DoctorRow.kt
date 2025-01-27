package com.example.hospitalturnmanagement.ui.doctors

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
import com.example.hospitalturnmanagement.data.model.Doctor

@Composable
fun DoctorRow(
    doctor: Doctor, // Objeto Doctor que contiene la información a mostrar
    onDelete: (Int) -> Unit, // Callback para eliminar un doctor (recibe el ID del doctor)
    onEdit: (Doctor) -> Unit // Callback para editar un doctor (recibe el objeto Doctor completo)
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
            // Información del doctor
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = "Nombre: ${doctor.firstName} ${doctor.lastName}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Correo: ${doctor.email}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Teléfono: ${doctor.phone}",
                    fontSize = 14.sp
                )
                Text(
                    text = "Horario: ${doctor.availabilityRange}",
                    fontSize = 14.sp
                )
                Text(
                    text = "Especialidad: ${doctor.specialty}",
                    fontSize = 14.sp
                )
            }

            // Botones de acción
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { onEdit(doctor) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = { doctor.id?.let { onDelete(it) } }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}
