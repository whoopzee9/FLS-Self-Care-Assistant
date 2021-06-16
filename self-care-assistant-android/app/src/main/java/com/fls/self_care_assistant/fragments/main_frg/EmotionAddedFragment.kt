package com.fls.self_care_assistant.fragments.main_frg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.databinding.FragmentEmotionAddedBinding
import com.fls.self_care_assistant.viewModels.EmotionAddedViewModel

class EmotionAddedFragment : Fragment() {

    private lateinit var viewModel: EmotionAddedViewModel
    private var _binding: FragmentEmotionAddedBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EmotionAddedViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmotionAddedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emotion: Emotion? = arguments?.getParcelable("emotion")

        binding.frgEmotionAddedTvEmotionDate.text = emotion?.date.toString()
        val emotionStr = resources.getString(R.string.emotion_type, emotion?.emotion)
        binding.frgEmotionAddedTvEmotionType.text = emotionStr
        val intensityStr = resources.getString(R.string.intensity, emotion?.intensity.toString())
        binding.frgEmotionAddedTvEmotionIntensity.text = intensityStr
    }
}