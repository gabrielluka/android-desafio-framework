package com.gabriel.desafio.view.fragments.todos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.desafio.R
import com.gabriel.desafio.data.model.TodosModel

class TodosAdapter() : RecyclerView.Adapter<TodosViewHolder>() {

    private var mTodosList : List<TodosModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_todos, parent, false)
        return TodosViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mTodosList.count()
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        holder.bind(mTodosList[position])
    }

    fun updateTodos(list: List<TodosModel>) {
        mTodosList = list
        notifyDataSetChanged()
    }
}