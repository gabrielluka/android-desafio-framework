package com.gabriel.desafio.view.fragments.posts

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
import com.gabriel.desafio.repository.PostsRepository
import com.gabriel.desafio.viewmodel.PostsViewModel

class PostsFragment : Fragment() {

    private val viewModel: PostsViewModel by lazy {
        ViewModelProvider(this,
            PostsViewModel.PostsViewModelFactory(PostsRepository()))
            .get(PostsViewModel::class.java)
    }

    private val adapter: PostsAdapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_posts, container, false)

        val recycler = mView.findViewById<RecyclerView>(R.id.rv_list_post)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        return mView
    }

    override fun onResume() {
        super.onResume()
        observerPosts()
        viewModel.loadPosts()

        val header = getActivity()?.findViewById<TextView>(R.id.tv_title_header);
        header?.text = "Posts"
    }

    private fun observerPosts() {
        viewModel.posts.observe(
            viewLifecycleOwner,
            Observer {
                if(it.second) {
                    Toast.makeText(context, Constantes.ERRO.NOT_FOUND_VALUE, Toast.LENGTH_LONG).show()
                } else {
                    adapter.updatePosts(it.first)
                }
            }
        )
    }

}