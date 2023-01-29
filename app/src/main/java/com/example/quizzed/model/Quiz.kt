package com.example.quizzed.model

data class Quiz(
    var id : String = "",
    var title : String = "",
    var questions : MutableMap<String, Questions> = mutableMapOf()
)


