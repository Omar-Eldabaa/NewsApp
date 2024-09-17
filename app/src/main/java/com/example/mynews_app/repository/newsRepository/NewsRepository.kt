package com.example.mynews_app.repository.newsRepository

import com.example.data.api.model.newsResponse.ArticlesItem

interface NewsRepository {
    suspend fun getNews(sourceId:String):List<ArticlesItem?>?
}