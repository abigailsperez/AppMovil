package com.demo.retrofitwithpost.model.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {

        val baseUrl = "http://192.168.1.68:8080"

        fun getRetroInstance(): Retrofit {

            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)

           return Retrofit.Builder()
                .baseUrl(baseUrl)
               .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}