package com.example.mynews_app.dataSource

import com.example.data.api.model.sourcesResponse.SourcesItem

interface SourcesDataSource {
    suspend fun getSources(categoryId:String):List<SourcesItem?>?
}

