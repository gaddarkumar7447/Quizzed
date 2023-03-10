package com.example.quizzed.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzed.R
import com.example.quizzed.model.Quiz
import com.example.quizzed.question.QuestionShow
import com.example.quizzed.util.ColorPicker
import com.example.quizzed.util.IconPicker
import com.google.android.material.bottomsheet.BottomSheetDialog

class QuizAdapter(private val context : Context, private val quizzes: List<Quiz>) : RecyclerView.Adapter<QuizAdapter.ViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        return ViewModel(LayoutInflater.from(context).inflate(R.layout.quizzed_item, parent, false))
    }
    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.quizTitle.text = quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColorPicker()))
        holder.quizIcon.setImageResource(IconPicker.getCurrent())
        holder.cardContainer.setOnClickListener {
            /*val view : View = LayoutInflater.from(context).inflate(R.layout.bottom_sheet, null)
            val dialog = BottomSheetDialog(context)
            view.findViewById<TextView>(R.id.tv_select_address).setOnClickListener{
                dialog.dismiss()
                Toast.makeText(context, quizzes[position].title, Toast.LENGTH_SHORT).show()
            }
            dialog.setContentView(view)
            dialog.show()*/

            val intent = Intent(context, QuestionShow::class.java)
            intent.putExtra("DATE", quizzes[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    class ViewModel(view : View) :RecyclerView.ViewHolder(view) {
        val quizTitle : TextView = view.findViewById(R.id.quizTitle)
        val quizIcon : ImageView = view.findViewById(R.id.quizIcon)
        val cardContainer : CardView = view.findViewById(R.id.cardContainer)
    }
}