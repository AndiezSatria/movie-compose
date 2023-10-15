package org.andiez.submissioncompose.alarm.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import org.andiez.common.util.CommonConstant
import org.andiez.submissioncompose.R

/**
 * Created by AndiezSatria on 07/09/2023.
 */

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val title = intent?.getStringExtra(CommonConstant.REMINDER_TITLE) ?: return
        val message = intent.getStringExtra(CommonConstant.REMINDER_MESSAGE) ?: return
        val channelId = "alarm_id"
        context?.let { ctx ->
            val notificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val builder = NotificationCompat.Builder(ctx, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            notificationManager.notify(CommonConstant.REMINDER_ID, builder.build())
        }
    }
}