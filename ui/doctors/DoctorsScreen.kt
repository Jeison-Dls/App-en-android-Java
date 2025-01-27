package com.example.hospitalturnmanagement.ui.doctors

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hospitalturnmanagement.data.model.Doctor
import com.example.hospitalturnmanagement.data.repository.DoctorsRepository
import androidx.compose.foundation.lazy.items

// Pantalla principal para la gestión de doctores
@Composable
fun DoctorsScreen(viewModel: DoctorsViewModel = viewModel(factory = DoctorsViewModelFactory(DoctorsRepository()))) {
    // Estado que contiene la lista de doctores desde el ViewModel
    val doctors by viewModel.doctors.collectAsState()
    // Estado que indica si los datos están cargándose
    val isLoading by viewModel.isLoading.collectAsState()
    // Estado para manejar el doctor actualmente seleccionado para edición
    var selectedDoctor by remember { mutableStateOf<Doctor?>(null) }

    // Efecto lanzado al cargar la pantalla para obtener los datos iniciales
    LaunchedEffect(Unit) {
        viewModel.fetchDoctors() // Llama al método del ViewModel para obtener doctores
    }

    // Estructura principal de la pantalla
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestión de Doctores", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        // Formulario para agregar o editar doctores
        DoctorForm(
            initialDoctor = selectedDoctor, // Doctor seleccionado
            onSubmit = { doctor ->
                if (selectedDoctor != null) {
                    // Si hay un doctor seleccionado, es una actualización
                    viewModel.updateDoctor(doctor.id!!, doctor)
                } else {
                    // Si no hay doctor seleccionado, es un nuevo registro
                    viewModel.addDoctor(doctor)
                }
                selectedDoctor = null // Limpia el estado después de enviar
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(doctors) { doctor ->
                    DoctorRow(
                        doctor = doctor,
                        onDelete = { viewModel.deleteDoctor(it) }, // Elimina el doctor con el ID proporcionado
                        onEdit = { selectedDoctor = it } // Selecciona el doctor para edición
                    )
                }
            }
        }
    }
}
