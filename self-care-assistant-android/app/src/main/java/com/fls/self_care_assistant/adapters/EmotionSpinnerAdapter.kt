package com.fls.self_care_assistant.adapters

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.fls.self_care_assistant.R
import java.util.*

class EmotionSpinnerAdapter(
    context: Context
) : ArrayAdapter<String>(context, 0) {
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.spinner_row_layout, parent, false)
        } else {
            view = convertView
        }
        getItem(position)?.let { str ->
            val tvStr = view.findViewById<TextView>(R.id.tv_string)
            tvStr.text = str
        }
        return view
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        if (position == 0) {
            view = layoutInflater.inflate(R.layout.spinner_row_layout, parent, false)
            view.setOnClickListener {
                val root = parent.rootView
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
            }
        } else {
            view = layoutInflater.inflate(R.layout.spinner_row_layout, parent, false)
            getItem(position)?.let { str ->
                val tvStr = view.findViewById<TextView>(R.id.tv_string)
                tvStr.text = str
            }
        }
        return view
    }

    override fun getItem(position: Int): String? {
        if (position == 0) {
            return null
        }
        return super.getItem(position - 1)
    }

    override fun getCount() = super.getCount() + 1
    override fun isEnabled(position: Int) = position != 0
}