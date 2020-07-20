package com.gabriel.desafio.view.fragments.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gabriel.desafio.R
import com.gabriel.desafio.data.Constantes
import com.gabriel.desafio.repository.AlbumsRepository
import com.gabriel.desafio.viewmodel.AlbumsViewModel

class AlbumsFragment : Fragment() {

    private val viewModel: AlbumsViewModel by lazy {
        ViewModelProvider(this,
            AlbumsViewModel.AlbumViewModelFactory(AlbumsRepository()))
            .get(AlbumsViewModel::class.java)
    }

    private val adapter = AlbumsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_albums, container, false)

        val recycler = mView.findViewById<RecyclerView>(R.id.rv_list_albums)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter

        return mView
    }

    override fun onResume() {
        super.onResume()
        observerAlbums()
        viewModel.loadAlbums()

        val header = getActivity()?.findViewById<TextView>(R.id.tv_title_header);
        header?.text = "Albums"
    }

    private fun observerAlbums() {
        viewModel.albums.observe(
            viewLifecycleOwner,
            Observer {
                if(it.second) {
                    Toast.makeText(context, Constantes.ERRO.NOT_FOUND_VALUE, Toast.LENGTH_LONG).show()
                } else {
                    adapter.updateAlbums(it.first)
                }
            }
        )
    }
}