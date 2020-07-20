package com.gabriel.desafio.data.services.api

import com.gabriel.desafio.data.Constantes
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceHTTP {
    private fun init(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constantes.SERVICE.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun <S> service(serviceClass: Class<S>) : S {
        return init().create(serviceClass)
    }
}

