package com.bsuir.myquizwithfirebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val username: String,
    val result: Int
)