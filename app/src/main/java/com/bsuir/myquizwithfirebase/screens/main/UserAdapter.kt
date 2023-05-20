package com.bsuir.myquizwithfirebase.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsuir.myquizwithfirebase.R
import com.bsuir.myquizwithfirebase.databinding.ItemUserBinding
import com.bsuir.myquizwithfirebase.model.User
import com.bumptech.glide.Glide

class UserAdapter (
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    class UserViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root)

    var users: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        with(holder.binding) {
            userNameTextView.text = "Username: ${user.username}"
            resultTextView.text = "Result: ${user.result}"
            Glide.with(photoImageView.context)
                .load(R.drawable.ic_person)
                .into(photoImageView)
        }
    }

    override fun getItemCount(): Int = users.size



}