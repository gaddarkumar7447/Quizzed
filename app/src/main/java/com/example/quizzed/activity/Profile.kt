package com.example.quizzed.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.quizzed.R
import com.example.quizzed.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    private lateinit var dataBinding : ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)


    }
}