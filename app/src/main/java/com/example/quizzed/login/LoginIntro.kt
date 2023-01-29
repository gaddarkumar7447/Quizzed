package com.example.quizzed.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.quizzed.MainActivity
import com.example.quizzed.R
import com.example.quizzed.databinding.ActivityLoginIntroBinding
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
    private lateinit var dataBinding : ActivityLoginIntroBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_intro)
        firebaseAuth = FirebaseAuth.getInstance()
        dataBinding.btnGetStarted.setOnClickListener(View.OnClickListener {
            gotoThisActivity("LogIn")
        })
        if (firebaseAuth.currentUser != null){
            gotoThisActivity("Main")
        }
    }

    private fun gotoThisActivity(name : String) {
        val intent = when(name){
            "LogIn" -> Intent(this, LogIn::class.java)
            "Main" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("No path exit")
        }
        startActivity(intent)
        finish()
    }
}