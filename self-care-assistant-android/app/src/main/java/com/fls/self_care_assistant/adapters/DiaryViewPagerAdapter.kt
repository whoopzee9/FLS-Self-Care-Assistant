package com.fls.self_care_assistant.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fls.self_care_assistant.fragments.main_frg.AddEmotionFragment
import com.fls.self_care_assistant.fragments.main_frg.HistoryFragment

class DiaryViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    val addRecord = AddEmotionFragment()
    val history = HistoryFragment()

    val fragmentList: ArrayList<Fragment> = arrayListOf(
        addRecord,
        history
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}