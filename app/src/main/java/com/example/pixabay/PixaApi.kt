package com.example.pixabay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("api/")
    fun getPictures(
        @Query("q") keyWord: String,
        @Query("key") key: String = "38416286-3c8df9fd925507debf3df3594",
        @Query("per_page") perPage: Int = 3,
        @Query("page") page: Int
    ): Call<PixaModel>
}