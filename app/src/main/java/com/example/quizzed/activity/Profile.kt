package com.example.quizzed.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.quizzed.R
import com.example.quizzed.autherization.LoginIntro
import com.example.quizzed.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class Profile : AppCompatActivity() {
    private lateinit var dataBinding : ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        // for show email address
        dataBinding.email = FirebaseAuth.getInstance().currentUser?.email.toString()

        logOut()
    }

    private fun logOut() {
        dataBinding.btnLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginIntro::class.java))
            finish()
        }
    }
}