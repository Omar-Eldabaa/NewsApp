package com.example.mynews_app.ui.newsDetails

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.data.api.model.newsResponse.ArticlesItem
import com.example.mynews_app.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel : NewsDetailsViewModel
    lateinit var binding: ActivityNewsDetailsBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =ViewModelProvider(this)[NewsDetailsViewModel::class.java]
        binding.lifecycleOwner=this
        initParams()
        bindData()
        binding.backButton.setOnClickListener{
            finish()
        }
        binding.linkTxt.setOnClickListener{
            openLinkInBrowser(viewModel.news?.url.toString())
        }
    }
    fun initParams(){
        val news = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("news", ArticlesItem::class.java)
        }else {
            intent.getParcelableExtra("news") as ArticlesItem?
        }
        viewModel.news =news
    }
    fun bindData(){
        binding.news =viewModel.news
    }
    fun openLinkInBrowser(link:String){
        val intent =Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(link))
        startActivity(intent)
    }
}