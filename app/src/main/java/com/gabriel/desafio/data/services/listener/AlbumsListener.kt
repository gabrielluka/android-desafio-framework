package com.gabriel.desafio.data.services.listener

import com.gabriel.desafio.data.model.AlbumsModel

interface AlbumsListener {
    fun onSuccess(albums: List<AlbumsModel>)
    fun onError(msg: String)
}