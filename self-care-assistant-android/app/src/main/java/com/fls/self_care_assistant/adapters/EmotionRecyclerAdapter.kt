package com.fls.self_care_assistant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.databinding.EmotionHeaderBinding
import com.fls.self_care_assistant.databinding.EmotionRowLayoutBinding
import java.text.SimpleDateFormat

class EmotionRecyclerAdapter(var values: MutableList<Emotion>, var onClickListener: OnClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    override fun getItemCount(): Int {
        return values.size + 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            TYPE_HEADER -> {
                val binding =
                    EmotionHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EmotionHeaderViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = EmotionRowLayoutBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                EmotionItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }

    override fun getItemViewType(position: Int) = when (position != 0) {
        true -> TYPE_ITEM
        else -> TYPE_HEADER
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EmotionHeaderViewHolder -> {

            }
            is EmotionItemViewHolder -> {
                val truePosition = position - 1
                holder.itemView.setOnClickListener {
                    onClickListener.onItemClick(truePosition)
                }
                with (holder.binding) {
                    val format = SimpleDateFormat("dd.MM.yyyy\nHH:mm:ss")
                    tvDate.text = format.format(values[truePosition].date)
                    tvEmotion.text = values[truePosition].emotion.name
                    tvStrength.text = values[truePosition].intensity.toString()
                }
            }
        }
    }

    fun setAdapterValues(value: ArrayList<Emotion>){
        this.values.clear()
        this.values.addAll(value)
    }

    private companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }
}

class EmotionItemViewHolder(val binding: EmotionRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

class EmotionHeaderViewHolder(val binding: EmotionHeaderBinding) : RecyclerView.ViewHolder(binding.root)



