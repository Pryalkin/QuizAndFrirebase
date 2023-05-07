package com.bsuir.myquizwithfirebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsuir.myquizwithfirebase.databinding.ItemUserBinding

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
            resultTextView.text = user.username + "     " + user.result.toString()
        }
    }

    override fun getItemCount(): Int = users.size



}