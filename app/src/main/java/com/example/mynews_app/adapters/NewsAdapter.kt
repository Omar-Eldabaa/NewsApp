package com.example.mynews_app.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.mynews_app.ui.news.OnClickNewsListener
import com.example.data.api.model.newsResponse.ArticlesItem
import com.example.mynews_app.databinding.ItemNewsBinding

class NewsAdapter(var newsList:List<ArticlesItem?>?=null):Adapter<NewsAdapter.NewsViewHolder>() {


    class NewsViewHolder(val itemNewsBinding: ItemNewsBinding):RecyclerView.ViewHolder(itemNewsBinding.root){

    }
    var onClickNewsListener : OnClickNewsListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val viewBinding =ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(viewBinding)
    }

    override fun getItemCount(): Int {
        return newsList?.size?:0
    }
    fun updateData(articles: List<ArticlesItem?>?) {
        newsList=articles
        notifyDataSetChanged()
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news =newsList!![position]
        holder.itemNewsBinding.news =news
        holder.itemNewsBinding.invalidateAll()

                holder.itemView.setOnClickListener {
                    onClickNewsListener?.onClick(news)
                }


    }
}