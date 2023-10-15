package org.andiez.common.util

import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {

    fun convertFormatDate(oldDate: String): String {
        if (oldDate == "") return "No Date"
        val oldFormat = "yyyy-MM-dd"
        val newFormat = "dd MMMM yyyy"
        val sdf = SimpleDateFormat(oldFormat, Locale.getDefault())
        val date = sdf.parse(oldDate)
        sdf.applyPattern(newFormat)
        return sdf.format(date!!)
    }

    fun getCalendarString(format: String, calendar: Calendar): String {
        val formatter = SimpleDateFormat(format, Locale("ID", "id"))
        return formatter.format(calendar.time)
    }
}