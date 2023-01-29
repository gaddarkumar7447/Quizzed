package com.example.quizzed.util

object ColorPicker {
    private val colorPicker = arrayOf(
        "#3EB9DF",
        "#3685BC",
        "#D36280",
        "#E44F55",
        "#FA8056",
        "#818BCA",
        "#7D659F",
        "#51BAB3",
        "#4FB66C",
        "#E3AD17",
        "#627991",
        "#EF8EAD",
        "#B5BFC6"
    )

    private var currentColorIndex = 0

    fun getColorPicker(): String {
        currentColorIndex = (currentColorIndex + 1) % colorPicker.size
        return colorPicker[currentColorIndex]
    }
}

