package com.example.quizzed.util

import com.example.quizzed.R

object IconPicker {
    private val iconPicker = arrayOf(
        R.drawable.ic_1,
        R.drawable.ic_2,
        R.drawable.ic_3,
        R.drawable.ic_4,
        R.drawable.ic_5,
        R.drawable.ic_6,
        R.drawable.ic_7,
        R.drawable.ic_8
    )
    var currentIcon = 0

    fun getCurrent() : Int{
        currentIcon = (currentIcon + 1) % iconPicker.size
        return iconPicker[currentIcon]
    }
}