package com.fls.self_care_assistant.fragments.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.databinding.LoginFragmentBinding
import com.fls.self_care_assistant.extensions.handleBackPressed
import com.fls.self_care_assistant.main.MainActivity
import com.fls.self_care_assistant.viewModels.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    companion object {
        const val RC_SIGN_IN = 100
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        val account = GoogleSignIn.getLastSignedInAccount(context)
        println(account?.email)
        //googleSignInClient.signOut()

        viewModel.tokenRepository.setupSharedPrefs(
            requireActivity().getSharedPreferences(
                "prefs",
                Context.MODE_PRIVATE
            )
        )

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.loginViewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.loginState.collect {
                handleUiState(it)
            }
        }

        binding.loginFrgSignBtn.setOnClickListener {
            validate()
        }
        binding.loginFrgRestoreBtn.setOnClickListener {
            binding.loginFrgAuthWr.visibility = View.GONE
            binding.loginFrgRestoreWr.visibility = View.VISIBLE
        }

        binding.loginFrgAuthVk.setOnClickListener {
            VK.login(requireActivity(), arrayListOf(VKScope.WALL, VKScope.PHOTOS))
        }
        binding.loginFrgAuthGoogle.setOnClickListener {
            //TODO maybe change because of deprecation
            val signInIntent: Intent = googleSignInClient.signInIntent
            requireActivity().startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        handleBackPressed {
            //todo Alert + optimize
            if (binding.loginFrgAuthWr.visibility == View.GONE) {
                binding.loginFrgAuthWr.visibility = View.VISIBLE
                binding.loginFrgRestoreWr.visibility = View.GONE
            } else {
                requireActivity().finish()
            }
        }
    }

    private fun handleUiState(state: LoginViewModel.LoginState) {
        when (state) {
            is LoginViewModel.LoginState.Failure -> {
                binding.loginFrgProgressBar.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    getString(R.string.unknown_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
            is LoginViewModel.LoginState.Initial -> {
                binding.loginFrgProgressBar.visibility = View.GONE
            }
            is LoginViewModel.LoginState.Processing -> {
                binding.loginFrgProgressBar.visibility = View.VISIBLE
            }
            is LoginViewModel.LoginState.Success -> {
                binding.loginFrgProgressBar.visibility = View.GONE
                Toast.makeText(
                    requireContext(),
                    getString(R.string.successful_login),
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
    }

    private fun validate() {
        if (viewModel.validateInput()) {
            viewModel.signIn()
        } else {
            Toast.makeText(requireContext(), "Please, fill all fields", Toast.LENGTH_SHORT).show()
        }
    }
}