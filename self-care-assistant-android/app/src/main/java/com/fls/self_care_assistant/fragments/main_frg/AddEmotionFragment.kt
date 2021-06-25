package com.fls.self_care_assistant.fragments.main_frg

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.adapters.DiaryViewPagerAdapter
import com.fls.self_care_assistant.data.EmotionType
import com.fls.self_care_assistant.databinding.FragmentAddEmotionBinding
import com.fls.self_care_assistant.databinding.FragmentDiaryBinding
import com.fls.self_care_assistant.fragments.auth.AuthActivity
import com.fls.self_care_assistant.main.MainActivity
import com.fls.self_care_assistant.network.DiaryError
import com.fls.self_care_assistant.repositories.TokenRepository
import com.fls.self_care_assistant.viewModels.AddEmotionViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AddEmotionFragment : Fragment() {

    private lateinit var viewModel: AddEmotionViewModel
    private var _binding: FragmentAddEmotionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddEmotionViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEmotionBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.addEmotionViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.resetAddEmotionState()
        lifecycleScope.launch {
            viewModel.addEmotionState.collect {
                handleUiState(it)
            }
        }

        val adapter = ArrayAdapter<EmotionType>(requireContext(), R.layout.spinner_row_layout, R.id.tv_emotion)
        binding.frgAddEmotionSpEmotion.adapter = adapter

        viewModel.getEmotionTypes()

        lifecycleScope.launch {
            viewModel.emotionTypes.collectLatest {
                adapter.addAll(it)
            }
        }

        binding.frgAddEmotionSpEmotion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View?, selectedItemPosition: Int, selectedId: Long
            ) {
                viewModel.setSpinnerPosition(selectedItemPosition)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        viewModel.getSpinnerPosition().observe(viewLifecycleOwner, {
            binding.frgAddEmotionSpEmotion.setSelection(it)
        })

        binding.frgAddEmotionMbAddEmotion.setOnClickListener {
            viewModel.addNewEmotion() {
                val bundle = Bundle()
                bundle.putParcelable("emotion", it)

                viewModel.getEmotionHistory()

                findNavController().navigate(R.id.action_addEmotionFragment_to_emotionAddedFragment, bundle)
            }

        }
    }

    private fun setActionsClickability(isClickable: Boolean) {
        binding.frgAddEmotionMbAddEmotion.isClickable = isClickable
    }

    private fun handleUiState(state: AddEmotionViewModel.AddEmotionState) {
        when (state) {
            is AddEmotionViewModel.AddEmotionState.Failure -> {
                binding.frgAddEmotionProgressBar.visibility = View.GONE
                if (state.addEmotionError is DiaryError.InvalidCredentials) {
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
            is AddEmotionViewModel.AddEmotionState.Initial -> {
                binding.frgAddEmotionProgressBar.visibility = View.GONE
                setActionsClickability(true)
            }
            is AddEmotionViewModel.AddEmotionState.Processing -> {
                binding.frgAddEmotionProgressBar.visibility = View.VISIBLE
                setActionsClickability(false)
            }
            is AddEmotionViewModel.AddEmotionState.Success -> {
                binding.frgAddEmotionProgressBar.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    getString(R.string.emotion_added),
                    Toast.LENGTH_SHORT
                ).show()
                setActionsClickability(true)
            }
        }
    }
}