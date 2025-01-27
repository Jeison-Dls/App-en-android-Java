package com.example.hospitalturnmanagement.utils

import java.text.SimpleDateFormat
import java.util.*

fun calculateAge(dateOfBirth: String): Int? {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val birthDate = sdf.parse(dateOfBirth) ?: return null
        val today = Calendar.getInstance()
        val birth = Calendar.getInstance()
        birth.time = birthDate

        var age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        age
    } catch (e: Exception) {
        null
    }
}
