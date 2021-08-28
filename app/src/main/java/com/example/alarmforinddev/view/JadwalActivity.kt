package com.example.alarmforinddev.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.Toast
import com.example.alarmforinddev.R
import com.example.alarmforinddev.service.MyBroadcastReceiver

class JadwalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)



        val buttonStop : Button = findViewById(R.id.shutUp)
        var mp: MediaPlayer = MediaPlayer.create(applicationContext, R.raw.alarm_for_health)
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        mp.start()

            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        100000,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                vibrator.vibrate(100000)
            }


        var  sec = 43200
        var intentBroadcast = Intent(applicationContext, MyBroadcastReceiver::class.java)
        var pendingIntent =  PendingIntent.getBroadcast(applicationContext, 111, intentBroadcast, 0)
        var alarmStart : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmStart.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (sec*1000), pendingIntent)


        buttonStop.setOnClickListener {
            mp.stop()
            vibrator.cancel()
            val toast = Toast.makeText(
                applicationContext,
                "Alarm Dihentikan",
                Toast.LENGTH_LONG
            )
            toast.show()
        }

    }
}