package com.mbobiosio.countries.api

import com.google.gson.GsonBuilder
import com.mbobiosio.countries.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {


    companion object {

        private lateinit var mAPIService: APIService
        private var mRetrofit: RetrofitClient? = null

        fun getRetrofit(): RetrofitClient {
            if (mRetrofit == null) {
                synchronized(this) {
                    mRetrofit = RetrofitClient()
                }
            }
            return mRetrofit!!
        }
    }

    init {

        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(0, TimeUnit.SECONDS)
                .readTimeout(0, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        mAPIService = retrofit.create(APIService::class.java)
    }

    fun getAPIService() = mAPIService

}