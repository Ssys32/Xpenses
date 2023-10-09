package com.example.xpenses.common.utils

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DomainUtils @Inject constructor() {

    fun getToday(): String {
        return getFormatDate(Calendar.getInstance().time)
    }

    fun getFormatDate(date: Date): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(date)
    }

}