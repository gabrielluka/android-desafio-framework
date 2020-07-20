package com.gabriel.desafio.data.services.listener

import com.gabriel.desafio.data.model.TodosModel

interface TodosListener {
    fun onSuccess(todos: List<TodosModel>)
    fun onError(msg: String)
}