package com.example.quizzed.activity

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzed.R
import com.example.quizzed.databinding.ActivityResultBinding
import com.example.quizzed.model.Questions
import com.example.quizzed.model.Quiz
import com.google.gson.Gson

class Result : AppCompatActivity() {
    private lateinit var dataBinding : ActivityResultBinding
    private lateinit var quiz : Quiz
    private var question : MutableList<Questions> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        setUpView()
    }

    private fun setUpView() {
        val json = intent.getStringExtra("QUIZE")
        quiz = Gson().fromJson(json, Quiz::class.java)

        for (entry in quiz.questions.entries){
            question.addAll(listOf(entry.value))
            //Log.d("questionAll", "fromOfJson: $question")
        }
        setAnswerView(question)
        Log.d("TAG", "list: $question")
        calculateScore()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAnswerView(question: List<Questions>) {
        /*val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#FF000000'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dataBinding.answerText.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            dataBinding.answerText.text = Html.fromHtml(builder.toString());
        }*/


        val answerAdapter = AnswerAdapter(this, question)
        dataBinding.recyclerViewAnswer.layoutManager = LinearLayoutManager(this)
        dataBinding.recyclerViewAnswer.setHasFixedSize(true)
        dataBinding.recyclerViewAnswer.adapter = answerAdapter
        answerAdapter.notifyDataSetChanged()
    }

    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries ) {
            val question = entry.value
            //Log.d("Question", "Question: $question")
            if (question.userAnswer == question.answer){
                score += 10
            }
        }
        dataBinding.score = "Your score: $score"
        //Log.d("Score","Score: $score")
    }
}

