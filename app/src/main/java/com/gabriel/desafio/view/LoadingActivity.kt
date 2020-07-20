package com.gabriel.desafio.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gabriel.desafio.R
import com.gabriel.desafio.repository.LoadingRepository
import com.gabriel.desafio.viewmodel.LoadingViewModel

class LoadingActivity : AppCompatActivity() {

    private val viewModel: LoadingViewModel by lazy {
        ViewModelProvider(this,
            LoadingViewModel.LoadingViewModelFactory(LoadingRepository()))
            .get(LoadingViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        supportActionBar!!.hide()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchAll()

        viewModel.isFinishFetch.observe(this, Observer { isFinishing ->
            if(isFinishing) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }


}