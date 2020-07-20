package com.gabriel.desafio.view.fragments.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.desafio.R
import com.gabriel.desafio.data.model.PostsModel

class PostsAdapter() : RecyclerView.Adapter<PostsViewHolder>() {

    private var mPostsLists : List<PostsModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
        return PostsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mPostsLists.count()
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind(mPostsLists[position])
    }

    fun updatePosts(list: List<PostsModel>) {
        mPostsLists = list
        notifyDataSetChanged()
    }


}