package com.bsuir.myquizwithfirebase

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bsuir.myquizwithfirebase.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val pager = binding.pager
        val pageAdapter: FragmentStateAdapter = PageAdapter(this)
        pager.adapter = pageAdapter

        val tabLayout: TabLayout = binding.tabLayout
        val tabLayoutMediator = TabLayoutMediator(tabLayout, pager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Меню"
                }
                1 -> {
                    tab.text = "Рейтинг"
                }
            }
        }
        tabLayoutMediator.attach()

    }

}