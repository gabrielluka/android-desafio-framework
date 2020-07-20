package com.gabriel.desafio.view.fragments.albums

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.desafio.R
import com.gabriel.desafio.data.model.AlbumsModel

class AlbumsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    fun bind(album: AlbumsModel) {
        val textTitleAlbum = itemView.findViewById<TextView>(R.id.tv_title_album)
        textTitleAlbum.text = album.title
    }
}