package com.fls.self_care_assistant.fragments.auth

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.fls.self_care_assistant.R
import com.fls.self_care_assistant.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initTabs()
    }

    private fun initTabs() {
        supportActionBar?.hide()
        val viewPager: ViewPager2 = findViewById(R.id.main_activity__view_pager)
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        val tabs: TabLayout = findViewById(R.id.main_activity__tabs)
        val titles = listOf("Sign In", "Sign Up")
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}