package com.example.mynews_app.ui.news

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.data.CategoryDataClass
import com.example.data.api.model.newsResponse.ArticlesItem
import com.example.data.api.model.sourcesResponse.SourcesItem
import com.example.mynews_app.R
import com.example.mynews_app.adapters.NewsAdapter
import com.example.mynews_app.databinding.ActivityMainBinding
import com.example.mynews_app.databinding.ActivityNewsBinding
import com.example.mynews_app.ui.ViewError
import com.example.mynews_app.ui.newsDetails.NewsDetailsActivity
import com.example.mynews_app.ui.showMessage
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    lateinit var viewModel:NewsViewModel
    lateinit var binding:ActivityNewsBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this)[NewsViewModel::class.java]
        initViews()
        initObservers()
        initParams()
        binding.back.setOnClickListener{
            finish()
        }
        viewModel.getNewsSources(viewModel.category?.id?:"")
        adapter.onClickNewsListener = object : OnClickNewsListener {
            override fun onClick(news: ArticlesItem?) {
                val intent = Intent(this@NewsActivity, NewsDetailsActivity::class.java)
                intent.putExtra("news" ,news)
                startActivity(intent)
            }

        }

    }

    private fun initObservers() {
        viewModel.shouldShowLoading.observe(this
        ) { show -> binding.progressBar.isVisible = show }

        viewModel.sourcesLiveData.observe(this){ sources->
            bindTab(sources)

        }
        viewModel.newsLiveData.observe(this){
            adapter.updateData(it)
        }
        viewModel.errorLiveData.observe(this){
            handleError(it)
        }
    }

    val adapter = NewsAdapter()
    private fun initViews() {
        binding.recyclerView.adapter = adapter
    }


    private fun bindTab(sources: List<SourcesItem?>?) {
        if (sources == null) return
        sources.forEach { sourcesItem ->
            val tab = binding.tabLayout.newTab()
            tab.text = sourcesItem?.name
            tab.tag = sourcesItem
            binding.tabLayout.addTab(tab)
            val layoutParams = LinearLayout.LayoutParams(tab.view.layoutParams)
            layoutParams.marginStart =12
            layoutParams.marginEnd =12
            layoutParams.bottomMargin =12
            layoutParams.topMargin =18
            tab.view.layoutParams=layoutParams
        }
        binding.tabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    viewModel.getNews(source.id)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {


                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    viewModel.getNews(source.id)

                }
            }
        )
        binding.tabLayout.getTabAt(0)?.select()
    }


    fun handleError(viewError: ViewError){
        showMessage(
            message=viewError.message ?:viewError.throwable?.localizedMessage?: "Something Went Wrong",
            posActionName = "try again",
            posAction = { dialogInterface, i ->
                dialogInterface.dismiss()
                viewError.onTryAgainClickListener?.onTryAgainClick()
            }

        )

    }
    fun initParams(){
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("key",CategoryDataClass::class.java)
        }else {
            intent.getParcelableExtra("key") as CategoryDataClass?
        }
        viewModel.category=data
    }


    }