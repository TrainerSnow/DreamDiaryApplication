package com.snow.dreamdiary.feature_dailysurvey.domain.util.services;

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.core.app.NotificationCompat
import com.snow.dreamdiary.R

const val CHANNEL_ID = "channel_id_notif_daily_survey_hbfjvgkfhjbvdkdvhjbfbhdjvfkdbvhfkbhvdfjxvfdxvfdxhj"
const val NOTIF_ID = 342536456

public class DailySurveyReminderReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if(context == null) return

        val name = context.getString(R.string.daily_survey_notif_channel)
        val descriptionText = context.getString(R.string.daily_survey_notif_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        val notif = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_survey)
            .setContentTitle(context.getString(R.string.daily_survey_notif_title))
            .setContentText(context.getString(R.string.daily_survey_notif_desc))
            .setAutoCancel(true)
            .build()

        notificationManager.notify(NOTIF_ID, notif)
    }

}