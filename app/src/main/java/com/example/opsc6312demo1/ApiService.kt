package com.example.opsc6312demo1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    fun getPost(
        @Query("userId") userId: Int,
        @Query("id") id: Int
    ): Call<List<Post>>
}

