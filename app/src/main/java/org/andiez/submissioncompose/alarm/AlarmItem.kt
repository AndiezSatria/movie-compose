package org.andiez.submissioncompose.alarm

import java.util.Calendar

/**
 * Created by AndiezSatria on 07/09/2023.
 */

data class AlarmItem(
    val alarmTime : Calendar,
    val message : String,
    val title: String,
)