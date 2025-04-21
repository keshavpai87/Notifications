package com.example.notificationsdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {



    private val channelID = "com.example.notificationsdemo.channel1"
    private var notificationManager :  NotificationManager?  = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnNotification = findViewById<Button>(R.id.btnNotifications)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(channelID, "Demo Channel", "This is a demo")

        btnNotification.setOnClickListener {
            displayNotification()
        }
    }

    // Displays an Icon, Title and Description
    private fun displayNotification() {
        val notificationId = 45
        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is Demo Notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id : String, name: String, channelDescription : String) {
        val importance = NotificationManager.IMPORTANCE_HIGH

        /*val channel = NotificationChannel(id, name, importance)
        channel.description = channelDescription*/

        // The Kotlin Way
        val channel = NotificationChannel(id, name, importance).apply {
            description = channelDescription
        }

        // Registering the notification channel with the system using the notification manager instance
        notificationManager?.createNotificationChannel(channel)
    }
}