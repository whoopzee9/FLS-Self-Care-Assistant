package com.fls.self_care_assistant.fragments.main_frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.databinding.FragmentContainerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiaryContainerFragment : Fragment() {

    private var _binding: FragmentContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController()
//        return navController.navigateUp()
//    }
}