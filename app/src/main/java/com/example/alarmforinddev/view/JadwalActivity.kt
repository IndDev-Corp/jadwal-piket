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
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.alarmforinddev.R
import com.example.alarmforinddev.model.TaskPiket
import com.example.alarmforinddev.presenter.TaskJadwalAdapter
import com.example.alarmforinddev.service.ApiService
import com.example.alarmforinddev.service.MyBroadcastReceiver
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import org.json.JSONArray
import org.json.JSONObject

class JadwalActivity : AppCompatActivity() {
    private val TAG = "JadwalActivity"
    private val arrayTask = ArrayList<TaskPiket>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal)

        getJadwalPiket(this)

    }

    private fun getJadwalPiket( context: JadwalActivity) {
        ApiService.getJadwal().getAsJSONArray(object : JSONArrayRequestListener {
            override fun onResponse(response: JSONArray?) {
                if (response != null) {
                    for (i in 0 until response.length()) {
                        Log.d(TAG, "OnErrorBody" + response[i])
                        if(response[i].toString() == "null" || response[i].toString() == "" ){
                            Log.d(TAG, "null kondisi" + response[i])
                        }else{
                            val item = response.getJSONObject(i)
                            val namePic = item?.getString("name")
                            val dateThisPic = item?.getString("date")
                            val activityPic = item?.getString("activity")
                            arrayTask.add(TaskPiket(activityPic,namePic,dateThisPic))
                            val myAdapterTask =TaskJadwalAdapter(arrayTask)
                            var recylerviewTask = context.findViewById<RecyclerView>(R.id.recyclerTask)
                            recylerviewTask.layoutManager = LinearLayoutManager(this@JadwalActivity)
                            recylerviewTask.adapter = myAdapterTask
                            Log.d(TAG, "OnSuccessBody $namePic$dateThisPic$activityPic")
                        }
                    }
                }
            }

            override fun onError(anError: ANError?) {
                Log.d(TAG, "OnErrorBody " + anError?.errorBody)
            }


        })
    }
}