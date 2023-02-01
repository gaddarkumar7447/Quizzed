package com.example.quizzed.question

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzed.R
import com.example.quizzed.model.Questions

class OptionAdapter (val context : Context, private val questions: Questions) : RecyclerView.Adapter<OptionAdapter.QuestionItem>(){
    private var options : List<String> = listOf(questions.option1, questions.option2, questions.option3, questions.option4)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionItem {
        return QuestionItem(LayoutInflater.from(context).inflate(R.layout.option_item, parent, false))
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: QuestionItem, position: Int) {
        holder.optionView.text = options[position]
        holder.optionView.setOnClickListener(View.OnClickListener {
            questions.userAnswer = options[position]
            notifyDataSetChanged()
        })
        if (questions.userAnswer == options[position]){
            holder.optionView.setBackgroundResource(R.drawable.select_option)
        }else{
            holder.optionView.setBackgroundResource(R.drawable.edit_text_bac)
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }

    class QuestionItem(view: View) : RecyclerView.ViewHolder(view) {
        val optionView : TextView = view.findViewById(R.id.questionOption)
    }
}