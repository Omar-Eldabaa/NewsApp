package com.example.data.api

import com.example.data.api.model.newsResponse.NewsResponse
import com.example.data.api.model.sourcesResponse.SourcesResponse
import com.example.mynews_app.ui.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {
    @GET("top-headlines/sources")
    suspend fun getSources(@Query("apiKey")apiKey:String = Constant.API_KEY ,@Query("category")
    category: String)
    : SourcesResponse

    @GET("everything")
    suspend fun getNews(@Query("apiKey")apiKey:String = Constant.API_KEY,@Query("sources")source:String)
    : NewsResponse

}