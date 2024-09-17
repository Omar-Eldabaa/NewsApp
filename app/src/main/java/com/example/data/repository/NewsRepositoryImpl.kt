package com.example.data.repository

import com.example.data.api.model.newsResponse.ArticlesItem
import com.example.mynews_app.dataSource.NewsDataSource
import com.example.mynews_app.repository.newsRepository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsDataSource:NewsDataSource):NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {
        val newsSource = newsDataSource.getNews(sourceId)
        return newsSource
    }
}