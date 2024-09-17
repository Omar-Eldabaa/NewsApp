package com.example.data.dataSource

import com.example.data.api.NewsServices
import com.example.data.api.model.sourcesResponse.SourcesItem
import com.example.mynews_app.dataSource.SourcesDataSource
import javax.inject.Inject

class SourcesOnlineDataSourceImpl @Inject constructor(private val newsServices: NewsServices):SourcesDataSource {
    override suspend fun getSources(categoryId: String): List<SourcesItem?>? {
        val response =newsServices.getSources(category = categoryId)
        return response.sources
    }

}
