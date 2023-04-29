package com.bsuir.myquizwithfirebase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bsuir.myquizwithfirebase.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater)
        binding.mainPlay.setOnClickListener {
            val dialog = NameDialog()
            dialog.show(requireActivity().supportFragmentManager, null)
        }
        return binding.root
    }


}