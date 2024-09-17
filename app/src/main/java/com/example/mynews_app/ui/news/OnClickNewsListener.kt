package com.example.mynews_app.ui.news

import com.example.data.api.model.newsResponse.ArticlesItem

interface OnClickNewsListener {
    fun onClick(news: ArticlesItem?)
}