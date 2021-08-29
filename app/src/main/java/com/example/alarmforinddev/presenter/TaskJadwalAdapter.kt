package com.example.alarmforinddev.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmforinddev.R
import com.example.alarmforinddev.model.TaskPiket
import kotlinx.android.synthetic.main.list_task_row.view.*

class TaskJadwalAdapter( val arrayList: ArrayList<TaskPiket>) :
    RecyclerView.Adapter<TaskJadwalAdapter.ViewHolder> () {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(model: TaskPiket){
           itemView.taskToDo.text = model.task
           itemView.namePicA.text = model.pic
           itemView.datePic.text = model.date

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_task_row, parent, false)
        return TaskJadwalAdapter.ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}