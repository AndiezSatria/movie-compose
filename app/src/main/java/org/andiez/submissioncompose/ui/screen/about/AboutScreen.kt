package org.andiez.submissioncompose.ui.screen.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.ajalt.timberkt.d
import org.andiez.common.ui.component.button.PrimaryRegularButton
import org.andiez.common.ui.theme.ContentPrimary
import org.andiez.common.util.CommonUtils
import org.andiez.submissioncompose.alarm.AlarmItem
import org.andiez.submissioncompose.alarm.AlarmScheduler
import org.andiez.submissioncompose.alarm.AlarmSchedulerImpl
import java.util.Calendar

/**
 * Created by AndiezSatria on 15/10/2023.
 */

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    var alarmItem: AlarmItem?
    val alarmScheduler: AlarmScheduler = AlarmSchedulerImpl(LocalContext.current)
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            "Created by Mohammad Andiez Satria Permana",
            style = MaterialTheme.typography.body1.copy(
                color = ContentPrimary,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryRegularButton(
            onClick = {
                val time = Calendar.getInstance()
                time.set(Calendar.MINUTE, time.get(Calendar.MINUTE) + 1)
                d {
                    "Alarm will notify at : ${
                        CommonUtils.getCalendarString(
                            "EEEE, dd MMMM yyyy HH:mm",
                            time
                        )
                    }"
                }
                alarmItem = AlarmItem(time, "Movie App", "Hello from Andiez.")
                alarmItem?.let { alarmScheduler.schedule(it) }
            },
            text = "Notify",
        )
    }
}