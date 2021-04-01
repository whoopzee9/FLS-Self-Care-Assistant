package com.fls.self_care_assistant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.data.Emotion
import java.text.SimpleDateFormat

class EmotionRecyclerAdapter(var values: ArrayList<Emotion>): RecyclerView.Adapter<EmotionRecyclerViewHolder>() {

    override fun getItemCount(): Int {
        return this.values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionRecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.emotion_row_layout, parent, false)
        return EmotionRecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmotionRecyclerViewHolder, position: Int) {
        holder.setDateText(values[position].date.toString())
        val format = SimpleDateFormat("dd.MM.yyyy\nHH:mm:ss")
        holder.setDateText(format.format(values[position].date))
        holder.setEmotionText(values[position].emotion)
        holder.setStrengthText(values[position].strength.toString())
    }

    fun setAdapterValues(value: ArrayList<Emotion>){
        this.values.clear()
        this.values.addAll(value)
    }
}
