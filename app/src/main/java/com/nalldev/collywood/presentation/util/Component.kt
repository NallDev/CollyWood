package com.nalldev.collywood.presentation.util

import java.text.SimpleDateFormat
import java.util.Locale

object Component {
    fun changeDateToYear(dateString: String) : String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            val date = dateFormat.parse(dateString)
            val yearFormat = SimpleDateFormat("yyyy", Locale.getDefault())
            yearFormat.format(date)
        } catch (e: Exception) {
            "XXXX"
        }
    }
}