package com.gabriel.desafio.data.services.listener

import com.gabriel.desafio.data.model.PostsModel

interface PostsListener {
    fun onSuccess(posts: List<PostsModel>)
    fun onError(msg: String)
}