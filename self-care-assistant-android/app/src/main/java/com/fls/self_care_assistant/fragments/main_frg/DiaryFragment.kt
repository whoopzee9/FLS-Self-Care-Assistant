package com.fls.self_care_assistant.fragments.main_frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fls.self_care_assistant.adapters.DiaryViewPagerAdapter
import com.fls.self_care_assistant.databinding.FragmentDiaryBinding
import com.fls.self_care_assistant.viewModels.DiaryViewModel
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


class DiaryFragment : Fragment() {

    private lateinit var viewModel: DiaryViewModel
    private var _binding: FragmentDiaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiaryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiaryBinding.inflate(inflater, container, false)
        initTabs()

        return binding.root
    }

    private fun initTabs() {
        val adapter = DiaryViewPagerAdapter(this)
        binding.frgDiaryViewPager.adapter = adapter
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        val titles = listOf("Add record", "History")
        TabLayoutMediator(binding.frgDiaryTabs, binding.frgDiaryViewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

}