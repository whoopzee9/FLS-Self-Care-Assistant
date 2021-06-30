package com.fls.self_care_assistant.fragments.main_frg

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.adapters.EmotionFilterRecyclerAdapter
import com.fls.self_care_assistant.data.EmotionFilter
import com.fls.self_care_assistant.data.EmotionTypesFilterBody
import com.fls.self_care_assistant.databinding.FragmentFilterBinding
import com.fls.self_care_assistant.fragments.auth.AuthActivity
import com.fls.self_care_assistant.network.FilterError
import com.fls.self_care_assistant.repositories.TokenRepository
import com.fls.self_care_assistant.viewModels.FilterViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FilterFragment : DialogFragment() {


    private lateinit var viewModel: FilterViewModel
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: EmotionFilterRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(FilterViewModel::class.java)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.resetFilterState()
        lifecycleScope.launch {
            viewModel.filterState.collect {
                handleUiState(it)
            }
        }

        adapter = EmotionFilterRecyclerAdapter()
        binding.frgFilterRvEmotions.adapter = adapter

        with(binding) {
            frgFilterCustomDateFromLayout.visibility = View.GONE
            frgFilterCustomDateToLayout.visibility = View.GONE
            frgFilterRbCustom.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    frgFilterCustomDateFromLayout.visibility = View.VISIBLE
                    frgFilterCustomDateToLayout.visibility = View.VISIBLE
                } else {
                    frgFilterCustomDateFromLayout.visibility = View.GONE
                    frgFilterCustomDateToLayout.visibility = View.GONE
                }
            }

            frgFilterEtCustomDateFrom.isFocusable = false
            frgFilterEtCustomDateFrom.setOnClickListener {
                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { view, year, month, dayOfMonth ->
                        val date = LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0)
                        frgFilterEtCustomDateFrom.setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))

                        viewModel.setDateFrom(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    },
                    LocalDate.now().year,
                    LocalDate.now().monthValue - 1,
                    LocalDate.now().dayOfMonth
                )
                datePickerDialog.show()
            }

            frgFilterEtCustomDateTo.isFocusable = false
            frgFilterEtCustomDateTo.setOnClickListener {
                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { view, year, month, dayOfMonth ->
                        val date = LocalDateTime.of(year, month + 1, dayOfMonth, 0, 0)
                        frgFilterEtCustomDateTo.setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))

                        viewModel.setDateTo(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    },
                    LocalDate.now().year,
                    LocalDate.now().monthValue - 1,
                    LocalDate.now().dayOfMonth
                )
                datePickerDialog.show()
            }

            frgFilterRbAll.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    var date = LocalDateTime.of(1970, 1, 1, 0, 0)
                    viewModel.setDateFrom(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    date = LocalDateTime.of(2099, 12, 31, 0, 0)
                    viewModel.setDateTo(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                }
            }

            frgFilterRbLastWeek.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    var date = LocalDateTime.now().minusWeeks(1)
                    viewModel.setDateFrom(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    date = LocalDateTime.now()
                    viewModel.setDateTo(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                }
            }

            frgFilterRbLastMonth.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    var date = LocalDateTime.now().minusMonths(1)
                    viewModel.setDateFrom(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    date = LocalDateTime.now()
                    viewModel.setDateTo(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                }
            }

            lifecycleScope.launch {
                viewModel.emotionTypes.collect { list ->
                    adapter.bind(list.map {
                        EmotionFilter(false, it, "", "")
                    })
                }
            }


            frgFilterMbClearAll.setOnClickListener {
                adapter.clear()
                frgFilterRgDateRadioGroup.clearCheck()
                frgFilterEtCustomDateFrom.setText("")
                frgFilterEtCustomDateTo.setText("")
                viewModel.resetFilter()
            }

            frgFilterMbCancel.setOnClickListener {
                viewModel.getEmotionHistory()
                dismiss()
                //findNavController().navigate(R.id.action_filterFragment_to_diaryFragment)
            }

            frgFilterRbAll.isChecked = true

            frgFilterMbApply.setOnClickListener {
                val list = adapter.emotionsList
                list.forEach {
                    if (it.isSelected && (it.from.isEmpty() || it.to.isEmpty())) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.fill_all_fields),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }
                }
                viewModel.setSelectedEmotions(list.filter { it.isSelected }.map {
                    EmotionTypesFilterBody(it.emotionName, it.from, it.to)
                })
                viewModel.applyFilter()
            }
        }
    }

    private fun setActionsClickability(isClickable: Boolean) {
        binding.frgFilterMbApply.isClickable = isClickable
        binding.frgFilterMbClearAll.isClickable = isClickable
        binding.frgFilterMbCancel.isClickable = isClickable
    }

    private fun handleUiState(state: FilterViewModel.FilterState) {
        when (state) {
            is FilterViewModel.FilterState.Failure -> {
                //binding.frgHistoryProgressBar.visibility = View.GONE
                if (state.filterError is FilterError.InvalidCredentials) {
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
                setActionsClickability(true)
            }
            is FilterViewModel.FilterState.Initial -> {
                setActionsClickability(true)
            }
            is FilterViewModel.FilterState.Processing -> {
                setActionsClickability(false)
            }
            is FilterViewModel.FilterState.Success -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.filter_applied),
                    Toast.LENGTH_SHORT
                ).show()
                setActionsClickability(true)
                println(viewModel.repository._emotionDiary.value.size)
                dismiss()
                //findNavController().navigate(R.id.action_filterFragment_to_diaryFragment)
            }
        }
    }
}