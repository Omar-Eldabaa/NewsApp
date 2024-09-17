package com.example.mynews_app.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.CategoryDataClass
import com.example.mynews_app.ui.ViewError
import com.example.data.api.model.newsResponse.ArticlesItem
import com.example.data.api.model.newsResponse.NewsResponse
import com.example.data.api.model.sourcesResponse.SourcesItem
import com.example.data.api.model.sourcesResponse.SourcesResponse
import com.example.mynews_app.repository.newsRepository.NewsRepository
import com.example.mynews_app.repository.sourcesRepository.SourcesRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(val sourcesRepo:SourcesRepository,
       val newsRepo :NewsRepository
) : ViewModel() {
    var category: CategoryDataClass?=null
    val shouldShowLoading =MutableLiveData<Boolean>()
    val sourcesLiveData =MutableLiveData<List<SourcesItem?>?>()
    val newsLiveData =MutableLiveData<List<ArticlesItem?>?>()
    val errorLiveData =MutableLiveData<ViewError>()


    fun getNewsSources(categoryId:String) {
        viewModelScope.launch {
            shouldShowLoading.postValue(true)
            try {
                val sources = sourcesRepo.getSources(categoryId)
                sourcesLiveData.postValue(sources)
            } catch (e:HttpException) {
                val errorBodyJsonString = e.response()?.errorBody()?.string()
                val responseError =
                    Gson().fromJson(errorBodyJsonString, SourcesResponse::class.java)
                errorLiveData.postValue(
                    ViewError(
                        responseError.message
                    ) {
                        getNewsSources(categoryId)
                    }
                )
            } catch (e: Exception) {
                errorLiveData.postValue(
                    ViewError(
                        throwable = e
                    ) {
                        getNewsSources(categoryId)
                    }
                )
            } finally {
                shouldShowLoading.postValue(false)
            }
        }
    }



         fun getNews(sourceId: String?) {
            viewModelScope.launch {
                shouldShowLoading.postValue(true)
                try {
                    val sources = newsRepo.getNews(sourceId?:"")
                    newsLiveData.postValue(sources)
                }catch (e:HttpException){
                    val errorBodyJsonString = e.response()?.errorBody()?.string()
                    val response = Gson().fromJson(errorBodyJsonString, NewsResponse::class.java)
                    errorLiveData.postValue(
                        ViewError(
                            response.message
                        ) {
                            getNews(sourceId)
                        }
                    )
                }
                catch (e:Exception){
                    errorLiveData.postValue(
                        ViewError(
                            throwable = e
                        ) {
                            getNews(sourceId)
                        }
                    )
                }finally {
                    shouldShowLoading.postValue(false)
                }
            }


        }

    }