package com.bsuir.myquizwithfirebase.screens.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return PageFragment.newInstance(position)
    }

    override fun getItemCount(): Int {
        return 2
    }
}