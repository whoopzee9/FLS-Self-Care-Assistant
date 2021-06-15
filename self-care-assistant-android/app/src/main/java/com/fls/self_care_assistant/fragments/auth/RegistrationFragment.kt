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
import com.fls.self_care_assistant.R

import com.fls.self_care_assistant.databinding.FragmentRegistrationBinding

import com.fls.self_care_assistant.databinding.RegistrationFragmentBinding

import com.fls.self_care_assistant.main.MainActivity
import com.fls.self_care_assistant.viewModels.RegistrationViewModel

class RegistrationFragment : Fragment() {


    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return RegistrationViewModel() as T
            }
        }).get(RegistrationViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_registration,
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
    }

    private fun validateRegistration() {
        if (viewModel.validateInput()) {
            if (!viewModel.isEmail()) {
                Toast.makeText(requireContext(), "Input correct email", Toast.LENGTH_SHORT).show()
                return
            } else if (!viewModel.isAcceptPrivacy()) {
                Toast.makeText(requireContext(), "Please, accept privacy", Toast.LENGTH_SHORT).show()
                return
            } else if (viewModel.passwordsMatch()) {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
                return
            } else
                Toast.makeText(requireContext(), "Passwords don't match", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please, fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
}