package com.example.quizzed.autherization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.quizzed.MainActivity
import com.example.quizzed.R
import com.example.quizzed.databinding.ActivityLogInBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dataBinding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_log_in)
        firebaseAuth = FirebaseAuth.getInstance()
        dataBinding.btnLogin.setOnClickListener(View.OnClickListener {
            logInUser()
        })
        dataBinding.btnSignUp.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        })
    }

    private fun logInUser() {
        val email = dataBinding.etEmailAddress.text.toString().trim()
        val pass = dataBinding.etPassword.text.toString().trim()
        if (email.isBlank() || pass.isBlank()) {
            Toast.makeText(this, "Email and password can't be blank", Toast.LENGTH_SHORT).show()
            return
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(OnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "LogIn Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "Email and password invalid", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}