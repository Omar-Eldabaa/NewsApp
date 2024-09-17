package com.example.mynews_app.ui.mainActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.lifecycle.ViewModelProvider
import com.example.data.CategoryDataClass
import com.example.mynews_app.R
import com.example.mynews_app.adapters.CategoriesAdapter
import com.example.mynews_app.databinding.ActivityMainBinding
import com.example.mynews_app.ui.news.NewsActivity
import com.example.mynews_app.ui.news.OnClickCategoryListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewModel:MainActivityViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        showToggle()
        val adapter=CategoriesAdapter(viewModel.category())
       binding.recyclerViewCategories.adapter =adapter
        adapter.onClickCategoryListener =object :OnClickCategoryListener{
            override fun onCategoryClick(items: CategoryDataClass) {
                val intent=Intent(this@MainActivity,NewsActivity::class.java)
                intent.putExtra("key",items)
                startActivity(intent)

            }

        }
    }

    private fun showToggle() {
        val toggle =ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolBar,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_category -> {
                    showCategory()
                    finish()
                }

                R.id.nav_settings -> {
                    showSettingsActivity()
                }
            }
            binding.drawerLayout.closeDrawers()
            return@setNavigationItemSelectedListener true
        }
    }

    private fun showSettingsActivity() {
        //push settings activity
    }

    private fun showCategory() {
        val intent=Intent(this@MainActivity,MainActivity::class.java)
        startActivity(intent)
    }

    fun initViews(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =ViewModelProvider(this)[MainActivityViewModel::class.java]
    }
}