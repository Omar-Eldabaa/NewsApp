package com.example.mynews_app.ui.newsDetails

import androidx.lifecycle.ViewModel
import com.example.data.api.model.newsResponse.ArticlesItem

class NewsDetailsViewModel:ViewModel() {
    var news: ArticlesItem?= null
}