package com.fls.self_care_assistant.fragments.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.databinding.RegistrationFragmentBinding
import com.fls.self_care_assistant.main.MainActivity
import com.fls.self_care_assistant.viewModels.RegistrationViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {


    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.registration_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.registrationViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrationFrgSignUp.setOnClickListener {
            validateRegistration()
        }
        lifecycleScope.launch {
            viewModel.registrationState.collect {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(state: RegistrationViewModel.RegistrationState) {
        when (state) {
            is RegistrationViewModel.RegistrationState.Failure -> {
                binding.registrationFrgProgressBar.visibility = View.GONE
                Toast.makeText(requireContext(), getString(R.string.unknown_error), Toast.LENGTH_SHORT).show()
            }
            is RegistrationViewModel.RegistrationState.Initial -> {
                binding.registrationFrgProgressBar.visibility = View.GONE
            }
            is RegistrationViewModel.RegistrationState.Processing -> {
                binding.registrationFrgProgressBar.visibility = View.VISIBLE
            }
            is RegistrationViewModel.RegistrationState.Success -> {
                binding.registrationFrgProgressBar.visibility = View.GONE
                Toast.makeText(requireContext(), getString(R.string.registration_successful), Toast.LENGTH_SHORT).show()
                val tabs : TabLayout = requireActivity().findViewById(R.id.activity_auth__tabs)
                tabs.getTabAt(0)?.select()
            }
        }
    }

    private fun validateRegistration() {
        if (viewModel.validateInput()) {
            if (!viewModel.isEmail()) {
                Toast.makeText(requireContext(), "Input correct email", Toast.LENGTH_SHORT).show()
                return
            } else if (!viewModel.isAcceptPrivacy()) {
                Toast.makeText(requireContext(), "Please, accept privacy", Toast.LENGTH_SHORT)
                    .show()
                return
            } else if (viewModel.passwordsMatch()) {
                viewModel.signUp()
                return
            } else
                Toast.makeText(requireContext(), "Passwords don't match", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please, fill all fields", Toast.LENGTH_SHORT).show()
        }
    }


}