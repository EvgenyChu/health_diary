package ru.churkin.health_diary.ui.componentsUI

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun DatePicker(onSelect: (String) -> Unit, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int
    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            onSelect("$dayOfMonth.${if (month + 1 < 10) "0${month + 1}" else month + 1}.$year")
        }, year, month, day
    )
    datePickerDialog.setOnDismissListener { onDismiss() }
    datePickerDialog.show()
}

@Composable
fun TimePicker(onSelect: (String) -> Unit, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val hour: Int
    val min: Int
    val calendar = Calendar.getInstance()
    hour = calendar.get(Calendar.HOUR_OF_DAY)
    min = calendar.get(Calendar.MINUTE)

    val timePickerDialog = TimePickerDialog(
        context,
        { _, mHour : Int, mMinute: Int ->
            onSelect("$mHour:$mMinute")
        },
        hour,
        min,
        true
    )
    timePickerDialog.setOnDismissListener { onDismiss() }
    timePickerDialog.show()
}