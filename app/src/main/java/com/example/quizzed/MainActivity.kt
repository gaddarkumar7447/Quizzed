package com.example.quizzed

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizzed.adapter.QuizAdapter
import com.example.quizzed.databinding.ActivityMainBinding
import com.example.quizzed.model.Questions
import com.example.quizzed.model.Quiz
import com.example.quizzed.question.QuestionShow
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var troggle: ActionBarDrawerToggle
    private lateinit var adapter: QuizAdapter
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private var quiz = mutableListOf<Quiz>()

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(dataBinding.toolBar)

        troggle = ActionBarDrawerToggle(
            this,
            dataBinding.drawerLayout,
            R.string.app_name,
            R.string.app_name
        )
        dataBinding.drawerLayout.addDrawerListener(troggle)
        troggle.syncState()


        firebaseSetUp()
        dummyData()
        setUpAdapter()
        setUpDatePicker()
    }

    @SuppressLint("SimpleDateFormat")
    private fun setUpDatePicker() {
        dataBinding.timePicker.setOnClickListener(View.OnClickListener {
            /*val view: View = layoutInflater.inflate(R.layout.bottom_sheet, null)
            val dialog = BottomSheetDialog(this)
            view.findViewById<TextView>(R.id.tv_select_address)?.setOnClickListener {
                Log.d("TAG", "clicked")
                dialog.dismiss()
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
            }
            dialog.setContentView(view)
            dialog.show()*/
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "datePicker")
            datePicker.addOnPositiveButtonClickListener {
                //Log.d("Date", "Date: ${datePicker.headerText}")
                val dateFormate = SimpleDateFormat("dd/MM/yyyy")
                val date: String = dateFormate.format(Date(it))
                //Log.d("Date formate", "Date: $date")

                val intent = Intent(this, QuestionShow::class.java)
                intent.putExtra("DATE", date)
                startActivity(intent)

                /*val firestore = FirebaseFirestore.getInstance()
                var bool  = 0

                firestore.collection("quizzes").whereEqualTo("title", date).get().addOnSuccessListener {task ->
                    if (task != null && !task.isEmpty){
                        bool = 1
                    }
                }

                if (bool == 1) {
                    val intent = Intent(this, QuestionShow::class.java)
                    intent.putExtra("DATE", date)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "No available quize $date", Toast.LENGTH_SHORT).show()
                }*/

            }
            datePicker.addOnCancelListener {
                //Log.d("Date", "Cancel: ${datePicker.headerText}")
            }
            datePicker.addOnNegativeButtonClickListener {
                //Log.d("Date", "Negative: ${datePicker.headerText}")
            }
        })

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun firebaseSetUp() {
        firestore = FirebaseFirestore.getInstance()
        val collectionRefrences = firestore.collection("quizzes")
        collectionRefrences.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Some problem occurs", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            } else {
                //Log.d("Data", "Data: " + value.toObjects(Quiz::class.java))
                quiz.clear()
                quiz.addAll(value.toObjects(Quiz::class.java))
                //Log.d("QuizData", "Data: $quiz")
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun dummyData() {
        quiz.add(Quiz("1", "1"))
        quiz.add(Quiz("1", "2"))
        quiz.add(Quiz("1", "3"))
        quiz.add(Quiz("1", "4"))
        quiz.add(Quiz("1", "5"))
        quiz.add(Quiz("1", "6"))
        quiz.add(Quiz("1", "7"))
        quiz.add(Quiz("1", "8"))
        quiz.add(Quiz("1", "9"))
        quiz.add(Quiz("1", "10"))
        quiz.add(Quiz("1", "11"))
        quiz.add(Quiz("1", "12"))
        quiz.add(Quiz("1", "13"))
        quiz.add(Quiz("1", "14"))
        quiz.add(Quiz("1", "15"))
        quiz.add(Quiz("1", "16"))
        quiz.add(Quiz("1", "17"))
        quiz.add(Quiz("1", "18"))
        quiz.add(Quiz("1", "19"))
        quiz.add(Quiz("1", "20"))
        quiz.add(Quiz("1", "21"))
        quiz.add(Quiz("1", "22"))
        quiz.add(Quiz("1", "23"))
        quiz.add(Quiz("1", "24"))
        quiz.add(Quiz("1", "25"))
        quiz.add(Quiz("1", "26"))
    }

    private fun setUpAdapter() {
        adapter = QuizAdapter(this, quiz)

        dataBinding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        dataBinding.recyclerView.hasFixedSize()
        dataBinding.recyclerView.adapter = adapter
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (troggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


