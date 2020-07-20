package com.gabriel.desafio.data.services.api

import com.gabriel.desafio.data.model.AlbumsModel
import com.gabriel.desafio.data.model.PostsModel
import com.gabriel.desafio.data.model.TodosModel
import com.gabriel.desafio.data.Constantes
import io.reactivex.Single
import retrofit2.http.GET

interface ServiceAPI {
    @GET(Constantes.SERVICE.PATH_POST_URL)
    fun listPosts(): Single<List<PostsModel>>

    @GET(Constantes.SERVICE.PATH_ALBUMS_URL)
    fun listAlbums(): Single<List<AlbumsModel>>

    @GET(Constantes.SERVICE.PATH_TODOS_URL)
    fun listTodos(): Single<List<TodosModel>>
}