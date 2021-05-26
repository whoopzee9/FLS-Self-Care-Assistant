package com.fls.self_care_assistant.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fls.self_care_assistant.fragments.auth.LoginFragment
import com.fls.self_care_assistant.fragments.auth.RegistrationFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {


    val login = LoginFragment()
    val registration = RegistrationFragment()

    val fragmentList: ArrayList<Fragment> = arrayListOf(
        login,
        registration
    )

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}