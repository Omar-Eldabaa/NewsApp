package com.example.mynews_app.dataSource

import com.example.data.api.model.newsResponse.ArticlesItem
import java.util.LinkedList

interface NewsDataSource {
    suspend fun getNews(sourceId:String):List<ArticlesItem?>?
}