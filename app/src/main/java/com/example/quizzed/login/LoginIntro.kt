package com.example.quizzed.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.quizzed.R
import com.example.quizzed.databinding.ActivityLoginIntroBinding

class LoginIntro : AppCompatActivity() {
    private lateinit var dataBinding : ActivityLoginIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_intro)

    }
}