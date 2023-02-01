package com.example.quizzed.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzed.R
import com.example.quizzed.model.Questions

class AnswerAdapter(val context: Context, private val questions: List<Questions>) : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(LayoutInflater.from(context).inflate(R.layout.answer, parent, false))
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.ans.text = questions[position].answer
        holder.que.text = questions[position].description
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class AnswerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ans : TextView = view.findViewById(R.id.answer)
        val que : TextView = view.findViewById(R.id.question_Answer)
    }
}