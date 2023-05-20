package com.bsuir.myquizwithfirebase.model

data class Question(
    val id: Long,
    val question: String,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val correctAnswer: String,
    val url: String
)
