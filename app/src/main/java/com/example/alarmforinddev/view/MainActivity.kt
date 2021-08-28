package com.example.alarmforinddev.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.alarmforinddev.R
import com.example.alarmforinddev.service.MyBroadcastReceiver

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.startAlarm)
        var  sec = 15

        btnStart.setOnClickListener {
            val toast = Toast.makeText(
                applicationContext,
                "Memulai Ketertiban",
                Toast.LENGTH_LONG
            )
            toast.show()

            var intentBroadcast = Intent(applicationContext, MyBroadcastReceiver::class.java)
            var pendingIntent =  PendingIntent.getBroadcast(applicationContext, 111, intentBroadcast, 0)
            var alarmStart : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmStart.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (sec*1000), pendingIntent)

        }
    }
}