package com.dealtaskmobile.data.instance

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object{

        val BASE_URL = "https://api.giphy.com/v1/gifs/"

        fun getRetroInstance(): Retrofit{
            return Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}