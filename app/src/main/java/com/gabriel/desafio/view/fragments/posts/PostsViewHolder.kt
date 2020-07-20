package com.gabriel.desafio.view.fragments.posts

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.desafio.R
import com.gabriel.desafio.data.model.PostsModel

class PostsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind (mPost: PostsModel) {
        val textUserId = itemView.findViewById<TextView>(R.id.tv_user_id)
        val textTitlePost = itemView.findViewById<TextView>(R.id.tv_title_post)
        val textPostDescribe = itemView.findViewById<TextView>(R.id.tv_describe_post)

        val id = mPost.userId.toString();
        textUserId.text = "User $id"

        textTitlePost.text = mPost.title
        textPostDescribe.text = mPost.body
    }

}