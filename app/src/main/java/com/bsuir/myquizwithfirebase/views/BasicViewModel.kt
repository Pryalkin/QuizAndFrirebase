package com.bsuir.myquizwithfirebase.views

import androidx.lifecycle.ViewModel
import com.bsuir.myquizwithfirebase.Singletons
import com.bsuir.myquizwithfirebase.repository.Repository

class BasicViewModel (
    private val repository: Repository = Singletons.repository
): ViewModel() {

    fun setUsername(username: String) {
        repository.setUsername(username)
    }

    fun setResult(result: Int) {
        repository.setResult(result)
    }

    fun getUsername(): String {
        return repository.getUsername()!!
    }

    fun getResult(): Int {
        return repository.getResult()!!
    }


}