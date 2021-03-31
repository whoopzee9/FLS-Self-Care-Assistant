package com.fls.self_care_assistant.adapters

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fls.self_care_assistant.R

class EmotionRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var date: TextView = itemView.findViewById(R.id.tv_date)
    var emotion: TextView = itemView.findViewById(R.id.tv_emotion)
    var strength: TextView = itemView.findViewById(R.id.tv_strength)

    fun setDateText(str: String){
        date.text = str
    }

    fun setEmotionText(str: String){
        emotion.text = str
    }

    fun setStrengthText(str: String){
        strength.text = str
    }
}