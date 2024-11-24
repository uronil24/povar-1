package com.example.povar.presentation.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.povar.R
import com.example.povar.presentation.activity.MainActivity

class NotifyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        val messageBody = intent.getStringExtra("NOTIFY").orEmpty()

        notificationManager.send(
            context = context,
            messageTitle = context.resources.getString(R.string.notify_name),
            messageBody = messageBody,
        )
    }

    private fun NotificationManager.send(
        messageTitle: String,
        messageBody: String,
        context: Context
    ){
        val intent = Intent(context, MainActivity::class.java)

        val builder = NotificationCompat.Builder(
            context,
            context.getString(R.string.notify_id)
        )

        builder
            .setSmallIcon(R.drawable.notify)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
            .setContentText(messageBody)

        notify(0, builder.build())
    }
}