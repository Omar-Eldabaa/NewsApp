package com.example.data.repository

import com.example.data.dataSource.NewsOnlineDataSourceImpl
import com.example.data.dataSource.SourcesOnlineDataSourceImpl
import com.example.mynews_app.dataSource.NewsDataSource
import com.example.mynews_app.dataSource.SourcesDataSource
import com.example.mynews_app.repository.newsRepository.NewsRepository
import com.example.mynews_app.repository.sourcesRepository.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {
    @Binds
    abstract fun provideSourcesRepository(sourcesRepoImpl:SourcesRepositoryImpl):SourcesRepository

    @Binds
    abstract fun provideSourcesDataSource(
        sourcesOnlineDataSourceImpl: SourcesOnlineDataSourceImpl
    ):SourcesDataSource

    @Binds
    abstract fun provideNewsRepository(
        newsRepo:NewsRepositoryImpl
    ):NewsRepository

    @Binds
    abstract fun provideNewsDataSource(
        newsData:NewsOnlineDataSourceImpl
    ):NewsDataSource
}