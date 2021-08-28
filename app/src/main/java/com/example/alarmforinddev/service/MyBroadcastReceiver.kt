package com.example.alarmforinddev.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import com.example.alarmforinddev.R
import com.example.alarmforinddev.view.JadwalActivity

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        var i = Intent(p0, JadwalActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        p0?.startActivity(i)
    }
}