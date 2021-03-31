package com.fls.self_care_assistant.fragments.main_frg

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.adapters.EmotionRecyclerAdapter
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.databinding.FragmentDiaryBinding
import com.fls.self_care_assistant.viewModels.DiaryViewModel


class DiaryFragment : Fragment() {

    companion object {
        fun newInstance() = DiaryFragment()
    }

    private lateinit var viewModel: DiaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DiaryViewModel() as T
            }
        }).get(DiaryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDiaryBinding>(
            inflater,
            R.layout.fragment_diary,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.diaryViewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var emotionsList = resources.getStringArray(R.array.emotions).toList()
        viewModel.emotionsList = emotionsList as ArrayList<String>

        val spinner = requireView().findViewById<Spinner>(R.id.sp_emotion)

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                viewModel.selectedEmotionPosition = selectedItemPosition
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.rv_emotions)
        val adapter = EmotionRecyclerAdapter(ArrayList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getEmotionDiary().observe(viewLifecycleOwner, {
            adapter.values = it
            adapter.notifyDataSetChanged()
        })
    }

}