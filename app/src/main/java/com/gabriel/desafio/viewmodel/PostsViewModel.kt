package com.gabriel.desafio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabriel.desafio.data.model.PostsModel
import com.gabriel.desafio.repository.PostsRepository
import com.gabriel.desafio.data.services.listener.PostsListener

class PostsViewModel(private val mPostsRepository: PostsRepository): ViewModel() {

    private val mPostsList = MutableLiveData<Pair<List<PostsModel>, Boolean>>()
    val posts: LiveData<Pair<List<PostsModel>, Boolean>> = mPostsList

    fun loadPosts () {
            mPostsRepository.getPosts(object : PostsListener {
                override fun onSuccess(posts: List<PostsModel>) {
                    mPostsList.value = Pair(posts, false)
                }

                override fun onError(msg: String) {
                    mPostsList.value = Pair(listOf(), true)
                }
            })
    }

    class PostsViewModelFactory(
        private val mPostsRepository: PostsRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PostsViewModel(mPostsRepository) as T
        }
    }

}