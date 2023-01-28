package com.example.quizzed.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.quizzed.MainActivity
import com.example.quizzed.R
import com.example.quizzed.databinding.ActivitySignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dataBinding : ActivitySignUpBinding
    var flag = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        firebaseAuth = FirebaseAuth.getInstance()
        dataBinding.btnLogin.setOnClickListener(View.OnClickListener {signUpUser()})
        dataBinding.btnSignUp.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, LogIn::class.java))
            finish()
        })
    }

    private fun signUpUser() {
        val email = dataBinding.etEmailAddress.text.toString().trim()
        val pass = dataBinding.etPassword.text.toString().trim()
        val conPass = dataBinding.etConformpassword.text.toString().trim()
        if (email.isBlank() || pass.isBlank() || conPass.isBlank()){
            Toast.makeText(this, "Email and password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass != conPass){
            Toast.makeText(this, "Password and conform password not match", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(OnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                else if (flag == 1){
                    Toast.makeText(this, "Email and password already exits please logIn", Toast.LENGTH_SHORT).show()
                }
                else{
                    flag = 1
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
        })

    }
}