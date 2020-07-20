package com.gabriel.desafio.repository

import android.util.Log
import com.gabriel.desafio.data.Constantes
import com.gabriel.desafio.data.model.AlbumsModel
import com.gabriel.desafio.data.entity.AlbumEntity
import com.gabriel.desafio.data.services.listener.AlbumsListener
import io.realm.Realm
import io.realm.exceptions.RealmPrimaryKeyConstraintException
import io.realm.kotlin.where

class AlbumsRepository {
    private val realm: Realm =  Realm.getDefaultInstance()

    fun getAlbums(listener: AlbumsListener)  {
        val listAlbums = mutableListOf<AlbumsModel>()
        val albums = realm.where(AlbumEntity::class.java).findAll()

        if(albums === null) {
            listener.onError(Constantes.ERRO.NOT_FOUND_VALUE)
        }

        albums.forEach {album ->
            listAlbums.add(AlbumsModel(id = album.id, userId = album.userId, title = album.title))
        }

        listener.onSuccess(listAlbums)
    }

    companion object {
        private val TAG = AlbumsRepository::class.java.simpleName

        fun saveAlbums(albums: List<AlbumsModel>, mRealm: Realm) {
            for(item in albums) {
                try {
                    val album = mRealm.where<AlbumEntity>().equalTo("id", item.id).findFirst()

                    if(album === null) {
                        val mAlbum = mRealm.createObject(AlbumEntity::class.java, item.id)
                        mAlbum.userId = item.userId
                        mAlbum.title = item.title
                    } else {
                        album.title = item.title
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