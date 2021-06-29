package com.fls.self_care_assistant.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fls.self_care_assistant.fragments.main_frg.AddEmotionFragment
import com.fls.self_care_assistant.fragments.main_frg.DiaryContainerFragment
import com.fls.self_care_assistant.fragments.main_frg.EmotionAddedFragment
import com.fls.self_care_assistant.fragments.main_frg.HistoryFragment
import com.google.android.material.tabs.TabLayout

class DiaryViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    val container = DiaryContainerFragment()
    val history = HistoryFragment()

    val fragmentList: ArrayList<Fragment> = arrayListOf(
        container,
        history
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}