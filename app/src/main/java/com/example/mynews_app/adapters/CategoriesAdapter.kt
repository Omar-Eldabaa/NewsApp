package com.example.mynews_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.data.CategoryDataClass
import com.example.mynews_app.databinding.ItemCategoryBinding
import com.example.mynews_app.ui.news.OnClickCategoryListener

class CategoriesAdapter(var items:List<CategoryDataClass>):Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(val itemBinding:ItemCategoryBinding):RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val viewBinding =ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(viewBinding)
    }
    var onClickCategoryListener:OnClickCategoryListener?=null

    override fun getItemCount(): Int =items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items =items[position]
        holder.itemBinding.imageCategory.setImageResource(items.imageRecourse)
        holder.itemBinding.textCategory.text=items.name
        holder.itemBinding.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,items.backgroundColor))
        holder.itemView.setOnClickListener {
            onClickCategoryListener?.onCategoryClick(items)
        }
    }
}