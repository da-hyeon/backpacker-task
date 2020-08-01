package com.hdh.backpacker_task.utils

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.hdh.backpacker_task.BuildConfig
import com.hdh.backpacker_task.data.ApiStores
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val mRetrofit: ApiStores by lazy {
        retrofit().create(ApiStores::class.java)
    }

    private fun retrofit(): Retrofit {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            builder.addInterceptor(loggingInterceptor)
            builder.addNetworkInterceptor(StethoInterceptor())
        }

        return Retrofit.Builder()
            .baseUrl("https://www.metaweather.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(
                builder
                    .addInterceptor { chain ->
                        val requestBuilder = chain.request().newBuilder()
                        chain.proceed(requestBuilder.build())
                    }
                    .build())
            .build()
    }
}