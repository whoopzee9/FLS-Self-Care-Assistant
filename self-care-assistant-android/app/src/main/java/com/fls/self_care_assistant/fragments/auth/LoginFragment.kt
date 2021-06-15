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
import com.fls.self_care_assistant.databinding.LoginFragmentBinding
import com.fls.self_care_assistant.extensions.handleBackPressed
import com.fls.self_care_assistant.main.MainActivity
import com.fls.self_care_assistant.viewModels.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

class LoginFragment : Fragment() {

    companion object {
        const val RC_SIGN_IN = 100
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginViewModel() as T
            }
        }).get(LoginViewModel::class.java)
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

    private fun validate() {
        if (viewModel.validateInput()) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        } else {
            Toast.makeText(requireContext(), "Please, fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    }
}