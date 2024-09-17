package com.example.mynews_app.ui.mainActivity

import androidx.lifecycle.ViewModel
import com.example.data.CategoryDataClass

class MainActivityViewModel:ViewModel() {

    fun category():List<CategoryDataClass>{
        return CategoryDataClass.getCategoryList()
    }

}