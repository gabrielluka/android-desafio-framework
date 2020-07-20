package com.gabriel.desafio.viewmodel

import androidx.lifecycle.*
import com.gabriel.desafio.data.model.TodosModel
import com.gabriel.desafio.repository.TodosRepository
import com.gabriel.desafio.data.services.listener.TodosListener

class TodosViewModel(private val mTodosRepository: TodosRepository): ViewModel() {

    private val mTodosList = MutableLiveData<Pair<List<TodosModel>, Boolean>>()
    val todos: LiveData<Pair<List<TodosModel>, Boolean>> = mTodosList

    fun getTodos() {
        mTodosRepository.getTodos(object : TodosListener {
            override fun onSuccess(todos: List<TodosModel>) {
                mTodosList.value = Pair(todos, false)
            }

            override fun onError(msg: String) {
                mTodosList.value = Pair(listOf(), true)
            }

        })

    }

    class TodosViewModelFactory(
        private val mTodosRepository: TodosRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TodosViewModel(mTodosRepository) as T
        }
    }
}