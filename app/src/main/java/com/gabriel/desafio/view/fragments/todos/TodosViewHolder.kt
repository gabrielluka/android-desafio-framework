package com.gabriel.desafio.view.fragments.todos

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.desafio.R
import com.gabriel.desafio.data.model.TodosModel

class TodosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(todos: TodosModel) {
        val textTodo = itemView.findViewById<TextView>(R.id.tv_label_todo)
        val iconCompleted = itemView.findViewById<ImageView>(R.id.iv_icon_completed)

        textTodo.text = todos.title

        iconCompleted
            .setColorFilter(if(todos.completed )Color.argb(255, 0, 156, 38) else Color.GRAY)
    }
}