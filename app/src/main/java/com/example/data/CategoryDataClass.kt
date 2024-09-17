package com.example.data

import android.graphics.drawable.Drawable
import android.os.Parcelable
import com.example.mynews_app.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryDataClass(
    var id :String,
    var name:String,
    var imageRecourse:Int,
    var backgroundColor:Int,
):Parcelable
{

    companion object{

        fun getCategoryList():List<CategoryDataClass>{
            return listOf(
                CategoryDataClass(
                    "sports",
                    "Sports",
                    R.drawable.ball,
                    R.color.red
                ),
                        CategoryDataClass(
                        "entertainment",
                "Politics",
                R.drawable.politics,
                R.color.blueBlack
            ),
                CategoryDataClass(
                    "health",
                    "Healthy",
                    R.drawable.health,
                    R.color.pink
                ),
                CategoryDataClass(
                    "business",
                    "Business",
                    R.drawable.health,
                    R.color.gold
                ),
                CategoryDataClass(
                    "general",
                    "Environment",
                    R.drawable.environment,
                    R.color.blueWhite
                ),
                CategoryDataClass(
                    "science",
                    "Science",
                    R.drawable.science,
                    R.color.lemon
                ),
            )
        }
    }
}

