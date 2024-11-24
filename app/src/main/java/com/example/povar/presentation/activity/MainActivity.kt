package com.example.povar.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.NotificationManagerCompat
import com.example.povar.app.PovarApplication
import com.example.povar.presentation.receiver.NotifyChannel
import com.example.povar.presentation.ui.theme.PovarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val channelManager: NotifyChannel by lazy {
        NotifyChannel(NotificationManagerCompat.from(this), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        channelManager.createNotificationChannels()
        setContent {
            PovarTheme {
                PovarApplication()
                    .PovarNavHost()
            }
        }
    }
}