package com.fls.self_care_assistant.fragments.main_frg

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.adapters.EmotionRecyclerAdapter
import com.fls.self_care_assistant.adapters.EmotionSpinnerAdapter
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.databinding.FragmentDiaryBinding
import com.fls.self_care_assistant.viewModels.DiaryViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DiaryFragment : Fragment() {

    /*companion object {
        fun newInstance() = DiaryFragment()
    }*/

    private lateinit var viewModel: DiaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DiaryViewModel() as T
            }
        }).get(DiaryViewModel::class.java)

        println(viewModel.getEmotionStrength().value)
        println(viewModel.getSpinnerPosition().value)
        println(viewModel.getEmotionDiary().value?.size)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentDiaryBinding>(
            inflater,
            R.layout.fragment_diary,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.diaryViewModel = viewModel
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var emotionsList = resources.getStringArray(R.array.emotions).toList()
        viewModel.emotionsList = emotionsList as ArrayList<String>

        val spinner = requireView().findViewById<Spinner>(R.id.sp_emotion)
        /*val spAdapter = EmotionSpinnerAdapter(requireContext())
        spinner.adapter = spAdapter*/
        /*val spAdapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, R.array.emotions)
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spAdapter*/

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
                viewModel.setSpinnerPosition(selectedItemPosition)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.rv_emotions)
        val adapter = EmotionRecyclerAdapter(ArrayList(), object: EmotionRecyclerAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                editEmotion(position)
            }

        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getEmotionDiary().observe(viewLifecycleOwner, {
            adapter.values = it
            adapter.notifyDataSetChanged()
        })
        viewModel.getSpinnerPosition().observe(viewLifecycleOwner, {
            spinner.setSelection(it)
        })
    }

    private fun editEmotion(position: Int) {
        val placeFormView = LayoutInflater.from(requireContext()).inflate(R.layout.emotion_edit_dialog_layout, null)
        val ETDate = placeFormView.findViewById<EditText>(R.id.et_Date)
        val ETTime = placeFormView.findViewById<EditText>(R.id.et_Time)
        val spinner = placeFormView.findViewById<Spinner>(R.id.sp_edit_emotion)
        val seekBar = placeFormView.findViewById<SeekBar>(R.id.sb_edit_emotion)
        val emotion = viewModel.getEmotionDiary().value?.get(position)
        val format = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
        ETDate.setText(format.format(emotion?.date).substringBefore(' '))
        ETTime.setText(format.format(emotion?.date).substringAfter(' '))
        seekBar.setProgress(emotion!!.strength, false)
        spinner.setSelection(viewModel.emotionsList.indexOf(emotion.emotion))

        ETDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                ETDate.setText(getFormattedDate(dayOfMonth, monthOfYear, year))

            }, year, month, day)

            dpd.show()
        }

        ETTime.setOnClickListener {
            val c = Calendar.getInstance()
            val time = TimePickerDialog(requireContext(), { view, hourOfDay, minute ->
                ETTime.setText(getFormattedTime(hourOfDay, minute))
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
            time.show()
        }

        val dialog = AlertDialog.Builder(requireContext())
                .setTitle("Редактирование состояния")
                .setView(placeFormView)
                .setNegativeButton("Отмена", null)
                .setPositiveButton("Подтвердить", null)
                .show()

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            val dateTime: String = ETDate.text.toString() + " " + ETTime.text.toString()
            //firebaseDB.createEvent(name.toString(), dateTime, key)
            dialog.dismiss()
            viewModel.editEmotion(spinner.selectedItemPosition, dateTime, seekBar.progress, position)
        }
    }

    private fun getFormattedTime(hour: Int, minute: Int): String {
        if ((hour >= 0) and (hour < 24) and (minute < 60) and (minute >= 0)) {
            val hourStr: String = if (hour < 10) {
                "0$hour"
            } else {
                hour.toString()
            }
            var minuteStr: String = if (minute < 10) {
                "0$minute"
            } else {
                minute.toString()
            }
            return "$hourStr:$minuteStr:00"
        } else {
            return "00:00:00"
        }
    }

    private fun getFormattedDate(day: Int, month: Int, year: Int): String {
        var dayStr = day.toString()
        var monthStr = (month + 1).toString()
        if (day < 10) {
            dayStr = "0$day"
        }
        if (month + 1 < 10) {
            monthStr = "0${month + 1}"
        }
        return "$dayStr.$monthStr.$year"
    }

}