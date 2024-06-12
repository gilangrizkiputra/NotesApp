package com.sukasrana.notesapp.utils

import android.annotation.SuppressLint
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Converter {
    @SuppressLint("NewApi")
    fun Long?.changeMillisToDateString(): String {
        val date: LocalDate = this?.let {
            Instant
                .ofEpochMilli(it)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        } ?: LocalDate.now()
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
    }
}