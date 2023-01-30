package com.example.quizzed.question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzed.R
import com.example.quizzed.databinding.ActivityQuestionShowBinding
import com.example.quizzed.model.Questions
import com.example.quizzed.model.Quiz

class QuestionShow : AppCompatActivity() {
    lateinit var adapter: OptionAdapter
    private lateinit var dataBinding : ActivityQuestionShowBinding
    private lateinit var questions: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_question_show)

        bindAdapter()
        //setQuizeQuestion()
    }


    private fun bindAdapter() {
        val option = Questions("What is your name", "Ram", "Lakhan", "Bharat", "SS")
        adapter = OptionAdapter(this, option)
        dataBinding.description.text = option.description
        dataBinding.optionList.layoutManager = LinearLayoutManager(this)
        dataBinding.optionList.adapter = adapter
        dataBinding.optionList.setHasFixedSize(true)
    }


}