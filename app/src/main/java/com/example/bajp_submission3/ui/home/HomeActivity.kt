package com.example.bajp_submission3.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.bajp_submission3.R
import com.example.bajp_submission3.databinding.ActivityHomeBinding
import com.example.bajp_submission3.ui.favorite.FavoriteActivity
import com.example.bajp_submission3.ui.movie.MovieFragment
import com.example.bajp_submission3.ui.tvshow.TvShowFragment
import com.example.bajp_submission3.utils.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setViewPager()
        menuBarNavigation()
    }

    private fun setViewPager() {
        val listFragment = listOf(MovieFragment(), TvShowFragment())
        val titleTab = listOf(
            getString(R.string.movie_tablayout),
            getString(R.string.tvshow_tablayout)
        )
        val iconTab = listOf(
            AppCompatResources.getDrawable(this, R.drawable.ic_movie),
            AppCompatResources.getDrawable(this, R.drawable.ic_tvshow)
        )

        binding.viewPager.adapter =
            ViewPagerAdapter(listFragment, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = titleTab[position]
            tab.icon = iconTab[position]
        }.attach()
    }

    private fun menuBarNavigation() {
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.main_favorite -> {
                    startActivity(Intent(this, FavoriteActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

}