package com.gabriel.desafio.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabriel.desafio.data.model.AlbumsModel
import com.gabriel.desafio.data.model.PostsModel
import com.gabriel.desafio.data.model.TodosModel
import com.gabriel.desafio.data.services.api.ServiceAPI
import com.gabriel.desafio.data.services.api.ServiceHTTP
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

class LoadingRepository() {
    private val TAG = LoadingRepository::class.java.simpleName

    private val mService =  ServiceHTTP.service(ServiceAPI::class.java)
    private val realm: Realm =  Realm.getDefaultInstance()

    private val mFinish =  MutableLiveData<Boolean>();
    val finished : LiveData<Boolean> = mFinish;

    private var responseAlbums: List<AlbumsModel> = listOf()
    private var responsePosts: List<PostsModel> = listOf()
    private var responseTodos: List<TodosModel> = listOf()

    fun fetchAll(compositeDisposable: CompositeDisposable){
        compositeDisposable.add(Single.zip(
            mService.listAlbums(),
            mService.listPosts(),
            mService.listTodos(),
            Function3<List<AlbumsModel>, List<PostsModel>, List<TodosModel>, Unit> {
                    response1, response2, response3 ->
                responseAlbums = response1
                responsePosts = response2
                responseTodos = response3

            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                saveAll()
            }, { erro ->
                Log.d(TAG, erro.message.toString())
            }))
    }

    private fun saveAll () {
        realm.executeTransactionAsync(Realm.Transaction { mRealm ->
            PostsRepository.savePosts(responsePosts, mRealm)
            AlbumsRepository.saveAlbums(responseAlbums, mRealm)
            TodosRepository.saveTodos(responseTodos, mRealm)
        }, Realm.Transaction.OnSuccess {
            mFinish.value = true
        }, Realm.Transaction.OnError {erro ->
            Log.e(TAG, erro.message.toString())
        })
    }

}