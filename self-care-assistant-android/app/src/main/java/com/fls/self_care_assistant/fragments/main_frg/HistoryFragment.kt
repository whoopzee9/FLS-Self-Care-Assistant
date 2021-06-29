package com.fls.self_care_assistant.fragments.main_frg

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.adapters.EmotionRecyclerAdapter
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.databinding.FragmentHistoryBinding
import com.fls.self_care_assistant.fragments.auth.AuthActivity
import com.fls.self_care_assistant.network.DiaryError
import com.fls.self_care_assistant.repositories.TokenRepository
import com.fls.self_care_assistant.viewModels.HistoryViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private lateinit var viewModel: HistoryViewModel

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.resetHistoryState()
        lifecycleScope.launch {
            viewModel.historyState.collect {
                handleUiState(it)
            }
        }

        val adapter =
            EmotionRecyclerAdapter(ArrayList(), object : EmotionRecyclerAdapter.OnClickListener {
                override fun onItemClick(position: Int) {
                    //editEmotion(position)
                }

                override fun onDeleteClick(position: Int) {
                    lifecycleScope.launch {
                        viewModel.emotionDiary.collect {
                            viewModel.deleteEmotion(it[position].id)
                        }
                    }
                }

            })
        viewModel.getEmotionHistory()
        binding.frgHistoryRvEmotions.adapter = adapter
        lifecycleScope.launch {
            viewModel.emotionDiary.collectLatest {
                adapter.values = it as MutableList<Emotion>
                adapter.notifyDataSetChanged()
            }
        }

        binding.frgHistoryMbFilter.setOnClickListener {
            val dialog = FilterFragment()
            dialog.show(parentFragmentManager, "tag")
            //findNavController().navigate(R.id.action_diaryFragment_to_filterFragment)
        }
    }

    private fun handleUiState(state: HistoryViewModel.HistoryState) {
        when (state) {
            is HistoryViewModel.HistoryState.Failure -> {
                binding.frgHistoryProgressBar.visibility = View.GONE
                if (state.historyError is DiaryError.InvalidCredentials) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.token_expired),
                        Toast.LENGTH_SHORT
                    ).show()
                    TokenRepository.instance.isExpired = true
                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.unknown_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            is HistoryViewModel.HistoryState.Initial -> {
                binding.frgHistoryProgressBar.visibility = View.GONE
            }
            is HistoryViewModel.HistoryState.Processing -> {
                binding.frgHistoryProgressBar.visibility = View.VISIBLE
            }
            is HistoryViewModel.HistoryState.Success -> {
                binding.frgHistoryProgressBar.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    getString(R.string.emotion_deleted),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    //    private fun editEmotion(position: Int) {
//        val placeFormView = LayoutInflater.from(requireContext()).inflate(R.layout.emotion_edit_dialog_layout, null)
//        val ETDate = placeFormView.findViewById<EditText>(R.id.et_Date)
//        val ETTime = placeFormView.findViewById<EditText>(R.id.et_Time)
//        val spinner = placeFormView.findViewById<Spinner>(R.id.sp_edit_emotion)
//        val seekBar = placeFormView.findViewById<SeekBar>(R.id.sb_edit_emotion)
//        val emotion = viewModel.getEmotionDiary().value?.get(position)
//        val format = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
//        ETDate.setText(format.format(emotion?.date).substringBefore(' '))
//        ETTime.setText(format.format(emotion?.date).substringAfter(' '))
//        seekBar.setProgress(emotion!!.strength, false)
//        spinner.setSelection(viewModel.emotionsList.indexOf(emotion.emotion))
//
//        ETDate.setOnClickListener {
//            val c = Calendar.getInstance()
//            val year = c.get(Calendar.YEAR)
//            val month = c.get(Calendar.MONTH)
//            val day = c.get(Calendar.DAY_OF_MONTH)
//            val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth ->
//
//                // Display Selected date in textbox
//                ETDate.setText(getFormattedDate(dayOfMonth, monthOfYear, year))
//
//            }, year, month, day)
//
//            dpd.show()
//        }
//
//        ETTime.setOnClickListener {
//            val c = Calendar.getInstance()
//            val time = TimePickerDialog(requireContext(), { view, hourOfDay, minute ->
//                ETTime.setText(getFormattedTime(hourOfDay, minute))
//            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)
//            time.show()
//        }
//
//        val dialog = AlertDialog.Builder(requireContext())
//                .setTitle("Редактирование состояния")
//                .setView(placeFormView)
//                .setNegativeButton("Отмена", null)
//                .setPositiveButton("Подтвердить", null)
//                .show()
//
//        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
//            val dateTime: String = ETDate.text.toString() + " " + ETTime.text.toString()
//            //firebaseDB.createEvent(name.toString(), dateTime, key)
//            dialog.dismiss()
//            viewModel.editEmotion(spinner.selectedItemPosition, dateTime, seekBar.progress, position)
//        }
//    }
//
//    private fun getFormattedTime(hour: Int, minute: Int): String {
//        if ((hour >= 0) and (hour < 24) and (minute < 60) and (minute >= 0)) {
//            val hourStr: String = if (hour < 10) {
//                "0$hour"
//            } else {
//                hour.toString()
//            }
//            var minuteStr: String = if (minute < 10) {
//                "0$minute"
//            } else {
//                minute.toString()
//            }
//            return "$hourStr:$minuteStr:00"
//        } else {
//            return "00:00:00"
//        }
//    }
//
//    private fun getFormattedDate(day: Int, month: Int, year: Int): String {
//        var dayStr = day.toString()
//        var monthStr = (month + 1).toString()
//        if (day < 10) {
//            dayStr = "0$day"
//        }
//        if (month + 1 < 10) {
//            monthStr = "0${month + 1}"
//        }
//        return "$dayStr.$monthStr.$year"
//    }
}