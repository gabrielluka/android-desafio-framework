package com.gabriel.desafio.view.fragments.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.desafio.R
import com.gabriel.desafio.data.model.AlbumsModel

class AlbumsAdapter() : RecyclerView.Adapter<AlbumsViewHolder>() {
    private var mAlbumsList : List<AlbumsModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_albums, parent, false)
        return AlbumsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mAlbumsList.count()
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(mAlbumsList[position])
    }

    fun updateAlbums(list: List<AlbumsModel>) {
        mAlbumsList = list
        notifyDataSetChanged()
    }

}