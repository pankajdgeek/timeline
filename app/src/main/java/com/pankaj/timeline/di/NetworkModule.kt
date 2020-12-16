package com.pankaj.timeline.di

import com.pankaj.timeline.network.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val URL = "https://jsonplaceholder.typicode.com"
val networkModule = module {

    single {
       return@single Retrofit
            .Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        return@single OkHttpClient()
            .newBuilder()
            .addInterceptor(logging)
            .build()
    }
    single {
       return@single (get() as Retrofit).create(Api::class.java) as Api
    }
}