package com.fls.self_care_assistant.fragments.main_frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.adapters.DiaryViewPagerAdapter
import com.fls.self_care_assistant.databinding.FragmentAddEmotionBinding
import com.fls.self_care_assistant.databinding.FragmentDiaryBinding
import com.fls.self_care_assistant.viewModels.AddEmotionViewModel

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

        var emotionsList = resources.getStringArray(R.array.emotions).toList()
        viewModel.emotionsList = emotionsList as ArrayList<String>

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

        binding.btnEmotion.setOnClickListener {
            val emotion = viewModel.addNewEmotion()
            val bundle = Bundle()
            bundle.putParcelable("emotion", emotion)

            findNavController().navigate(R.id.action_addEmotionFragment_to_emotionAddedFragment, bundle)
            //findNavController().navigate(R.id.emotionAddedFragment, bundle)
        }
    }
}