package com.gabriel.desafio.repository

import android.util.Log
import com.gabriel.desafio.data.Constantes
import com.gabriel.desafio.data.model.TodosModel
import com.gabriel.desafio.data.entity.TodoEntity
import com.gabriel.desafio.data.services.listener.TodosListener
import io.realm.Realm
import io.realm.exceptions.RealmPrimaryKeyConstraintException
import io.realm.kotlin.where

class TodosRepository  {
    private val realm: Realm =  Realm.getDefaultInstance()

    fun getTodos(listener: TodosListener)  {
        val listTodos = mutableListOf<TodosModel>()
        val todos = realm.where(TodoEntity::class.java).findAll()

        if(todos === null) {
            listener.onError(Constantes.ERRO.NOT_FOUND_VALUE)
        }
        todos.forEach {todo ->
            listTodos.add(TodosModel(id = todo.id, userId = todo.userId, title = todo.title, completed = todo.completed))
        }

        listener.onSuccess(listTodos)
    }

    companion object {
        private val TAG = TodosRepository::class.java.simpleName

        fun saveTodos(todos: List<TodosModel>, mRealm: Realm) {
            for(item in todos) {
                try {
                    val todo = mRealm.where<TodoEntity>().equalTo("id", item.id).findFirst()

                    if(todo === null) {
                        val mTodo = mRealm.createObject(TodoEntity::class.java, item.id)
                        mTodo.userId = item.userId
                        mTodo.title = item.title
                        mTodo.completed = item.completed
                    } else {
                        todo.title = item.title
                        todo.completed = item.completed
                    }

                } catch (e: RealmPrimaryKeyConstraintException) {
                    Log.d(TAG, e.message.toString())
                } catch (e: Exception) {
                    Log.d(TAG, e.message.toString())
                }
            }
        }
    }

}