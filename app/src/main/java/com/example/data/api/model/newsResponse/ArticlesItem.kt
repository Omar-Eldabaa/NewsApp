package com.example.data.api.model.newsResponse

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.data.api.model.sourcesResponse.SourcesItem
import com.google.gson.annotations.SerializedName

@Parcelize
data class ArticlesItem(

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: SourcesItem? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("content")
	val content: String? = null
) : Parcelable