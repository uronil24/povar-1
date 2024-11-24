package com.example.povar.presentation.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.povar.R

class NotifyChannel(
    private val notificationManager: NotificationManagerCompat,
    context: Context
) {
    private val defaultChannel = ChannelItem(
        id = context.resources.getString(R.string.notify_id),
        name = context.resources.getString(R.string.notify_name)
    )

    fun createNotificationChannels(){
        createNotificationChannelsSafety()
    }

    private fun createNotificationChannelsSafety() {
        createChannel(defaultChannel)
    }

    private fun createChannel(defaultChannel: ChannelItem) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(
            defaultChannel.id,
            defaultChannel.name,
            importance
        ).apply {
            enableLights(true)
            enableVibration(true)
            setShowBadge(true)
            lockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE
        }
        notificationManager.createNotificationChannel(channel)
    }
}