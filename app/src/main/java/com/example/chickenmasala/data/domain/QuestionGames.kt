package com.example.chickenmasala.data.domain

data class QuestionGames(
    val question:String,
    val correctAnswer:String,
    val incorrectAnswers:List<String>
)
