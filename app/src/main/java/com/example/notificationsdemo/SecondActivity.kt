package com.example.notificationsdemo

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        receiveInput()
    }


    fun receiveInput() {
        val KEY_REPLY = "key_reply"
        val channelID = "com.example.notificationsdemo.channel1"
        val notificationId = 45

        val intent = this.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if(remoteInput != null) {
            val userInput = remoteInput.getCharSequence(KEY_REPLY).toString()
            findViewById<TextView>(R.id.resultTV).text = userInput

            val repliedNotif = NotificationCompat.Builder(this, channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("Your reply received")
                .build()

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, repliedNotif)
        }
    }
}