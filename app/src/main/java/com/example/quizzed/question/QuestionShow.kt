package com.example.quizzed.question


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzed.R
import com.example.quizzed.databinding.ActivityQuestionShowBinding
import com.example.quizzed.model.Questions
import com.example.quizzed.model.Quiz
import com.google.firebase.firestore.FirebaseFirestore

class QuestionShow : AppCompatActivity() {
    lateinit var adapter: OptionAdapter
    private lateinit var dataBinding : ActivityQuestionShowBinding
    private var quizzes : MutableList<Quiz>? = null
    private var questions : MutableMap<String, Questions>? = null
    private var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_question_show)

        //bindAdapter()
        //setQuizeQuestion()
        setUpFirebaseStore()
        setUpButtonEvent()
    }

    private fun setUpButtonEvent() {

        dataBinding.btnPrevious.setOnClickListener{
            index--
            bindAdapter()
        }
        dataBinding.btnNext.setOnClickListener{
            index++
            /*if (index == questions!!.size){
                dataBinding.btnSubmit.visibility = View.VISIBLE
            }*/
            bindAdapter()
        }
        dataBinding.btnSubmit.setOnClickListener{
            Log.d("User Answer", questions.toString())
            finish()
        }
    }

    private fun setUpFirebaseStore() {
        val date = intent.getStringExtra("DATE")
        //Log.d("Date1", "Date : $date")
        val firebaseDate = FirebaseFirestore.getInstance().collection("quizzes").whereEqualTo("title", date).get()

        Log.d("DateCurrent", "Date : $firebaseDate")
        if (date != null){
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("quizzes").whereEqualTo("title", date).get().addOnSuccessListener {
                if (it != null && !it.isEmpty){
                    //Log.d("Quizzes", "Quizzes: "+it.toObjects(Quiz::class.java))
                    quizzes = it.toObjects(Quiz::class.java)
                    questions = quizzes!![0].questions
                    bindAdapter()
                }
            }
        }
    }

    private fun bindAdapter() {
        //val option = Questions("What is your name", "Ram", "Lakhan", "Bharat", "SS")

        val question = questions!!["question$index"]

        dataBinding.btnPrevious.visibility = View.INVISIBLE
        dataBinding.btnNext.visibility = View.INVISIBLE
        dataBinding.btnSubmit.visibility = View.INVISIBLE

        // for first question
        if (index == 1){
            dataBinding.btnNext.visibility = View.VISIBLE
        }// for last question
        else if (questions!!.size == index){
            dataBinding.btnPrevious.visibility = View.VISIBLE
            dataBinding.btnSubmit.visibility = View.VISIBLE
        }
        // middle
        else {
            dataBinding.btnPrevious.visibility = View.VISIBLE
            dataBinding.btnNext.visibility = View.VISIBLE
        }



        question.let {
            adapter = OptionAdapter(this, it!!)
            dataBinding.description.text = it.description
            dataBinding.optionList.layoutManager = LinearLayoutManager(this)
            dataBinding.optionList.adapter = adapter
            dataBinding.optionList.setHasFixedSize(true)
        }
    }




}