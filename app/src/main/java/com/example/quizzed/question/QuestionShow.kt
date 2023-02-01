package com.example.quizzed.question


import android.content.Intent
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
import com.google.gson.Gson
import com.example.quizzed.activity.Result

class QuestionShow : AppCompatActivity() {
    lateinit var adapter: OptionAdapter
    private lateinit var dataBinding : ActivityQuestionShowBinding
    private var quizzes : MutableList<Quiz>? = null
    private var questions : MutableMap<String, Questions>? = null
    private var index = 1
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_question_show)

        val date = intent.getStringExtra("DATE")
        //bindAdapter()
        //setQuizeQuestion()

        // for no question
        dataBinding.data = "No question found this date $date"

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
            //Log.d("User Answer", questions.toString())

            val intent = Intent(this,Result::class.java)
            val json : String = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZE",json)
            startActivity(intent)
            finish()
            //setUpScore()
            //Log.d("Json data", "Json Data: $json")
        }
    }

    private fun setUpScore() {
        var score = 0
        for (entry in questions!!.entries){
            val que = entry.value
            if (que.userAnswer == que.answer){
                score++
            }
        }
        Toast.makeText(this, score.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun checkAnswer() {
        val questions = Questions()
        val userAnswer = questions.userAnswer
        val currect = questions.answer
        if (userAnswer == currect){
            score++
        }
    }

    private fun setUpFirebaseStore() {
        val date = intent.getStringExtra("DATE")
        //Log.d("Date1", "Date : $date")

        //Log.d("DateCurrent", "Date : $firebaseDate")
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