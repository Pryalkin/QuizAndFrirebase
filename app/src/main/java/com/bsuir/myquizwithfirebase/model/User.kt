package com.bsuir.myquizwithfirebase.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val username: String,
    val result: Int
)