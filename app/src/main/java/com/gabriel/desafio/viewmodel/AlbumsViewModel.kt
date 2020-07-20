package com.gabriel.desafio.viewmodel

import androidx.lifecycle.*
import com.gabriel.desafio.data.model.AlbumsModel
import com.gabriel.desafio.data.services.listener.AlbumsListener
import com.gabriel.desafio.repository.AlbumsRepository

class AlbumsViewModel(private val mAlbumsRepository : AlbumsRepository): ViewModel()  {

    private val mAlbumsList = MutableLiveData<Pair<List<AlbumsModel>, Boolean>>()
    val albums: LiveData<Pair<List<AlbumsModel>, Boolean>> = mAlbumsList


    fun loadAlbums () {
        mAlbumsRepository.getAlbums(object : AlbumsListener {
            override fun onSuccess(albums: List<AlbumsModel>) {
                mAlbumsList.value = Pair(albums, false)
            }

            override fun onError(msg: String) {
                mAlbumsList.value = Pair(listOf(), true)
            }
        })
    }

    class AlbumViewModelFactory(
        private val mAlbumsRepository: AlbumsRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return AlbumsViewModel(mAlbumsRepository) as T
        }
    }
}