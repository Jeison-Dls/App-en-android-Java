package com.example.hospitalturnmanagement.utils

import android.util.Base64
import java.io.File
import java.io.FileInputStream

fun fileToBase64(file: File): String {
    val bytes = FileInputStream(file).use { it.readBytes() }
    return Base64.encodeToString(bytes, Base64.DEFAULT)
}
