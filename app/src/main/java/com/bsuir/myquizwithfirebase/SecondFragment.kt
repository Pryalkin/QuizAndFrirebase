package com.bsuir.myquizwithfirebase

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsuir.myquizwithfirebase.databinding.FragmentSecondBinding
import com.bsuir.myquizwithfirebase.model.User
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var adapter: UserAdapter
    private lateinit var users: List<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater)

        adapter = UserAdapter()

        val database = Firebase.database.reference
        database.child("users").get().addOnSuccessListener {
            val gson = Gson()
            val userListType: Type = object : TypeToken<List<User?>?>() {}.type
            val userArray: List<User> = gson.fromJson(it.value.toString(), userListType)
            adapter.users = userArray
            val layoutManager  = LinearLayoutManager(context)
            binding.recView.layoutManager = layoutManager
            binding.recView.adapter = adapter
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        return binding.root
    }


}

