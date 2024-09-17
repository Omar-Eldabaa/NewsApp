package com.example.mynews_app.repository.sourcesRepository

import com.example.data.api.model.sourcesResponse.SourcesItem

interface SourcesRepository {
    suspend fun getSources(categoryId:String):List<SourcesItem?>?
}