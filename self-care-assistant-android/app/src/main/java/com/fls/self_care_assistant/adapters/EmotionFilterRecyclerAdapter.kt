package com.fls.self_care_assistant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.fls.self_care_assistant.data.EmotionFilter
import com.fls.self_care_assistant.databinding.EmotionsRecyclerRowLayoutBinding

class EmotionFilterRecyclerAdapter: RecyclerView.Adapter<EmotionFilterItemViewHolder>() {
    var emotionsList: List<EmotionFilter> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmotionFilterItemViewHolder {
        val binding = EmotionsRecyclerRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmotionFilterItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmotionFilterItemViewHolder, position: Int) {
        with(holder.binding) {
            emotionsFilterItemCbEmotion.setOnCheckedChangeListener { buttonView, isChecked ->
                emotionsList[position].isSelected = isChecked
                    if (isChecked) {
                    emotionsFilterItemIntensityLayout.visibility = View.VISIBLE
                } else {
                    emotionsFilterItemIntensityLayout.visibility = View.GONE
                }
            }
            emotionsFilterItemCbEmotion.text = emotionsList[position].emotionName.name

            emotionsFilterItemEtIntensityFrom.addTextChangedListener {
                emotionsList[position].from = it.toString()
            }

            emotionsFilterItemEtIntensityTo.addTextChangedListener {
                emotionsList[position].to = it.toString()
            }
        }
    }

    override fun getItemCount() = emotionsList.size

    fun bind(emotions: List<EmotionFilter>) {
        emotionsList = emotions
        notifyDataSetChanged()
    }

    fun clear() {
        emotionsList.forEach {
            it.to = ""
            it.from = ""
            it.isSelected = false
        }
        notifyDataSetChanged()
    }
}

class EmotionFilterItemViewHolder(val binding: EmotionsRecyclerRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)