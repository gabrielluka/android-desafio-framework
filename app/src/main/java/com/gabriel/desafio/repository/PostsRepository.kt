package com.gabriel.desafio.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabriel.desafio.data.Constantes
import com.gabriel.desafio.data.model.PostsModel
import com.gabriel.desafio.data.entity.PostEntity
import com.gabriel.desafio.data.services.listener.PostsListener
import io.realm.Realm
import io.realm.exceptions.RealmPrimaryKeyConstraintException
import io.realm.kotlin.where

class PostsRepository {

    private val realm: Realm =  Realm.getDefaultInstance()

    fun getPosts(listener: PostsListener) {
        val listPostsModel = mutableListOf<PostsModel>()
        val posts = realm.where(PostEntity::class.java).findAll()

        if(posts === null) {
            listener.onError(Constantes.ERRO.NOT_FOUND_VALUE)
        }
        posts.forEach {post ->
            listPostsModel.add(PostsModel(id = post.id, userId = post.userId, title = post.title, body = post.title))
        }

        listener.onSuccess(listPostsModel)
    }

    companion object {
        private val TAG = PostsRepository::class.java.simpleName

        fun savePosts(posts: List<PostsModel>, mRealm: Realm) {
            for(item in posts) {
                try {
                    val post = mRealm.where<PostEntity>().equalTo("id", item.id).findFirst()

                    if(post === null) {
                        val mPost = mRealm.createObject(PostEntity::class.java, item.id)
                        mPost.userId = item.userId
                        mPost.title = item.title
                        mPost.body = item.body
                    } else {
                        post.title = item.title
                        post.body = item.body
                    }

                } catch (e: RealmPrimaryKeyConstraintException) {
                    Log.d(TAG, e.message.toString())
                } catch (e: Exception) {
                    Log.d(TAG, e.message.toString())
                }
            }
        }
    }


}
