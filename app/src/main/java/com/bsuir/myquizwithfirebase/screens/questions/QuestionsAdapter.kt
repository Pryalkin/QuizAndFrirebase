package com.bsuir.myquizwithfirebase.screens.questions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bsuir.myquizwithfirebase.screens.questions.QuestionsFragment

class QuestionsAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return QuestionsFragment.newInstance(position)
    }

    override fun getItemCount(): Int {
        return 11
    }
}