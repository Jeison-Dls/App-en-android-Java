package com.example.hospitalturnmanagement.ui.turnos

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hospitalturnmanagement.data.model.Doctor
import com.example.hospitalturnmanagement.data.model.Patient
import com.example.hospitalturnmanagement.ui.turnos.viewmodel.TurnosViewModel
import com.example.hospitalturnmanagement.ui.turnos.viewmodel.TurnosViewModelFactory
import com.example.hospitalturnmanagement.data.api.ApiClient
import com.example.hospitalturnmanagement.data.repository.TurnosRepository

@Composable
fun TurnosScreen() {
    // Inicializar ViewModel con el Factory
    val apiService = ApiClient.apiService
    val repository = TurnosRepository(apiService)
    val factory = TurnosViewModelFactory(repository)
    val viewModel: TurnosViewModel = viewModel(factory = factory)

    // Estados del ViewModel
    val appointments by viewModel.appointments.collectAsState()
    val doctors by viewModel.doctors.collectAsState()
    val patients by viewModel.patients.collectAsState()

    // Modales
    var isDoctorModalOpen by remember { mutableStateOf(false) }
    var isPatientModalOpen by remember { mutableStateOf(false) }

    // Campos seleccionados
    var selectedDoctor by remember { mutableStateOf<Doctor?>(null) }
    var selectedPatient by remember { mutableStateOf<Patient?>(null) }

    // Mapeo de turnos para mostrar nombres
    val mappedAppointments = appointments.map { appointment ->
        val doctorName = doctors.find { it.id == appointment.doctorId }?.let { "${it.firstName} ${it.lastName}" } ?: "Desconocido"
        val patientName = patients.find { it.id == appointment.patientId }?.let { "${it.firstName} ${it.lastName}" } ?: "Desconocido"
        appointment.copy(doctorName = doctorName, patientName = patientName)
    }

    // Layout general
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestión de Turnos", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))

        // Formulario para crear/actualizar turnos
        TurnosForm(
            selectedDoctor = selectedDoctor,
            selectedPatient = selectedPatient,
            onDoctorSearch = { isDoctorModalOpen = true },
            onPatientSearch = { isPatientModalOpen = true },
            onSubmit = { appointment ->
                viewModel.createAppointment(appointment)
                selectedDoctor = null
                selectedPatient = null
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tabla de turnos
        TurnosTable(
            appointments = appointments,
            onDelete = { id -> viewModel.deleteAppointment(id) },
            onEdit = { appointment ->
                selectedDoctor = doctors.find { it.id == appointment.doctorId }
                selectedPatient = patients.find { it.id == appointment.patientId }
            }
        )

        // Modal para seleccionar doctores
        if (isDoctorModalOpen) {
            MedicosModal(
                doctors = doctors,
                onDoctorSelect = { doctor ->
                    selectedDoctor = doctor
                    isDoctorModalOpen = false
                },
                onDismiss = { isDoctorModalOpen = false }
            )
        }

        // Modal para seleccionar pacientes
        if (isPatientModalOpen) {
            PacientesModal(
                patients = patients,
                onPatientSelect = { patient ->
                    selectedPatient = patient
                    isPatientModalOpen = false
                },
                onDismiss = { isPatientModalOpen = false }
            )
        }
    }

    // Cargar datos iniciales
    LaunchedEffect(Unit) {
        viewModel.fetchDoctors()
        viewModel.fetchPatients()
        viewModel.fetchAppointments()
    }
}
