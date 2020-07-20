package com.gabriel.desafio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gabriel.desafio.repository.LoadingRepository
import io.reactivex.disposables.CompositeDisposable

class LoadingViewModel(private val mRepository: LoadingRepository): ViewModel()  {

    private val compositeDisposable = CompositeDisposable()

    val isFinishFetch: LiveData<Boolean> = mRepository.finished

    fun fetchAll() {
        mRepository.fetchAll(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    class LoadingViewModelFactory(
        private val mRepository: LoadingRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoadingViewModel(mRepository) as T
        }
    }
}