package com.example.hospitalturnmanagement.ui.patients

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import com.example.hospitalturnmanagement.data.repository.PatientsRepository
import com.example.hospitalturnmanagement.data.model.Patient



@Composable
fun PatientsScreen(viewModel: PatientsViewModel = viewModel(factory = PatientsViewModelFactory(repository = PatientsRepository()))) {
    val patients by viewModel.patients.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Estado para el paciente seleccionado (para editar)
    val selectedPatient = remember { mutableStateOf<Patient?>(null) }

    // Llama a la función fetchPatients al iniciar la composición
    LaunchedEffect(Unit) {
        viewModel.fetchPatients()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Registro de Pacientes", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(16.dp))

        // Formulario para crear o actualizar pacientes
        PatientForm(
            initialPatient = selectedPatient.value, // Paciente seleccionado
            onSubmit = { patient ->
                if (selectedPatient.value != null) {
                    // Si hay un paciente seleccionado, es una actualización
                    viewModel.updatePatient(patient.id!!, patient)
                } else {
                    // Si no hay paciente seleccionado, es un nuevo registro
                    viewModel.addPatient(patient)
                }
                selectedPatient.value = null // Limpia el estado después de enviar
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(patients) { patient ->
                    PatientRow(
                        patient = patient,
                        onDelete = { patientId ->
                            viewModel.deletePatient(patientId)
                        },
                        onEdit = { editedPatient ->
                            selectedPatient.value = editedPatient // Al presionar editar, selecciona al paciente
                        }
                    )
                }
            }
        }
    }
}
