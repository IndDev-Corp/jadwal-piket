package com.example.alarmforinddev.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.alarmforinddev.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.startAlarm)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("haha", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result.toString()

            // Log and toast
//            val msg = getString(R.string.msg_token_fmt, token)
            Log.d("tokennya", token)
//            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })

        btnStart.setOnClickListener {
            val toast = Toast.makeText(
                applicationContext,
                "Memulai Ketertiban",
                Toast.LENGTH_LONG
            )
            toast.show()
            startActivity(Intent(this@MainActivity, JadwalActivity::class.java))

//            var intentBroadcast = Intent(applicationContext, MyBroadcastReceiver::class.java)
//            var pendingIntent =  PendingIntent.getBroadcast(applicationContext, 111, intentBroadcast, 0)
//            var alarmStart : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            alarmStart.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (sec*1000), pendingIntent)
//
//
        }
    }
}