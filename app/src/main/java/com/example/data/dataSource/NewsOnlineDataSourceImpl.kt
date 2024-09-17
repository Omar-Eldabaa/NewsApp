package com.example.data.dataSource

import android.annotation.SuppressLint
import com.example.data.api.NewsServices
import com.example.data.api.model.newsResponse.ArticlesItem
import com.example.mynews_app.dataSource.NewsDataSource
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(val newsServices: NewsServices):NewsDataSource {
    @SuppressLint("SuspiciousIndentation")
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {
        val response =newsServices.getNews(source = sourceId)
          return response.articles
    }
}