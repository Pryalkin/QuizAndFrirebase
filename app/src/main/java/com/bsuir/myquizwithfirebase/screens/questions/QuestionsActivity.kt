package com.bsuir.myquizwithfirebase.screens.questions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bsuir.myquizwithfirebase.R

class QuestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        val pager = findViewById<ViewPager2>(R.id.pager)
        val pageAdapter: FragmentStateAdapter = QuestionsAdapter(this)
        pager.isUserInputEnabled = false
        pager.adapter = pageAdapter
    }
}