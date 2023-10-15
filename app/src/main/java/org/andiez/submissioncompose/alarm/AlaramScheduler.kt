package org.andiez.submissioncompose.alarm

/**
 * Created by AndiezSatria on 07/09/2023.
 */

interface AlarmScheduler {
    fun schedule(alarmItem: AlarmItem)
    fun cancel(alarmItem: AlarmItem)
}