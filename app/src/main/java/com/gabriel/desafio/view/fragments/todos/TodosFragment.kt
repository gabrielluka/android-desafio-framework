package com.gabriel.desafio.view.fragments.todos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.desafio.R
import com.gabriel.desafio.data.Constantes
import com.gabriel.desafio.repository.TodosRepository
import com.gabriel.desafio.viewmodel.TodosViewModel

class TodosFragment : Fragment() {

    private val viewModel: TodosViewModel by lazy {
        ViewModelProvider(this,
            TodosViewModel.TodosViewModelFactory(TodosRepository()))
            .get(TodosViewModel::class.java)
    }

    private val adapter: TodosAdapter = TodosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        val mView = inflater.inflate(R.layout.fragment_todos, container, false)

        val recycler = mView.findViewById<RecyclerView>(R.id.rv_list_todos)

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        return mView
    }

    override fun onResume() {
        super.onResume()

        observerTodos()
        viewModel.getTodos()

        val header = getActivity()?.findViewById<TextView>(R.id.tv_title_header);
        header?.text = "To Do"
    }

    fun observerTodos() {
        viewModel.todos.observe(viewLifecycleOwner,
            Observer {
                if(it.second) {
                    Toast.makeText(context, Constantes.ERRO.NOT_FOUND_VALUE, Toast.LENGTH_LONG).show()
                } else {
                    adapter.updateTodos(it.first)
                }
            }
        )
    }
}