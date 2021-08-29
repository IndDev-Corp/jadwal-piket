package com.example.alarmforinddev.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.VibrationAttributes
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.alarmforinddev.R
import com.example.alarmforinddev.view.JadwalActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    /**
     * Called if the FCM registration token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the
     * FCM registration token is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        Log.d("HAHA", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
//        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        // Create an Intent for the activity you want to start
        val resultIntent = Intent(this, JadwalActivity::class.java)
        // Create the TaskStackBuilder
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
//        Log.d("RemoteMessage", p0.data.toString())
//        Log.d("RemoteMessage", p0.notification?.title.toString())
//        super.onMessageReceived(p0)
        var builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setContentIntent(resultPendingIntent)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(p0.data.get("title"))
            .setContentText(p0.data.get("body"))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line...")
            )
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setVibrate(longArrayOf(1000, 1000, 1000))
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
            .setCategory(NotificationCompat.CATEGORY_CALL)

//            .setPriority(NotificationManager.IMPORTANCE_HIGH)
        createNotificationChannel()
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(1, builder.build())
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = getString("CHANNEL_ID")
            val name = "CHANNEL_ID"
//            val descriptionText = getString(R.string.channel_description)
            val descriptionText = "Jeje"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
                setSound(
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE),
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION).build()
                )
                enableVibration(true)
                vibrationPattern = (longArrayOf(1000, 1000, 1000))
                shouldVibrate()
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}