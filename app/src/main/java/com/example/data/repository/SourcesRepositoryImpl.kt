package com.example.data.repository

import com.example.data.api.model.sourcesResponse.SourcesItem
import com.example.mynews_app.dataSource.SourcesDataSource
import com.example.mynews_app.repository.sourcesRepository.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(private val sourcesDataSource:SourcesDataSource): SourcesRepository {
    override suspend fun getSources(categoryId:String): List<SourcesItem?>? {
        val sources =sourcesDataSource.getSources(categoryId)
        return sources
    }
}