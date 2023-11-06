package com.example.comicslibrary.network

import com.example.comicslibrary.BuildConfig
import com.example.comicslibrary.util.getHash
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BASE_URL = "http://gateway.marvel.com/v1/public/"

    private fun getRetrofit() : Retrofit {
        val ts = System.currentTimeMillis().toString()
        val apiSecret = BuildConfig.MARVEL_SECRET
        val apiKey = BuildConfig.MARVEL_KEY
        val hash = getHash(ts, apiSecret, apiKey)

        val clientInterceptor = Interceptor {
            var request : Request = it.request()
            var url : HttpUrl = request.url.newBuilder()
                .addQueryParameter("ts",ts)
                .addQueryParameter("apikey",apiKey)
                .addQueryParameter("hash",hash)
                .build()
            request = request.newBuilder().url(url).build()
            it.proceed(request)
        }

        val client = OkHttpClient.Builder().addInterceptor(clientInterceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api : MarvelAPI = getRetrofit().create(MarvelAPI::class.java)
}