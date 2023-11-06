package com.example.comicslibrary.network

import com.example.comicslibrary.model.CharactersApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {
    @GET("characters")
    fun getCharacters(@Query("nameStartsWith") name : String) : retrofit2.Call<CharactersApiResponse>
}