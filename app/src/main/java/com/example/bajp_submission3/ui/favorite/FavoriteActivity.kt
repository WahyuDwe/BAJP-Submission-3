package com.example.bajp_submission3.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bajp_submission3.databinding.ActivityFavoriteBinding
import com.example.bajp_submission3.ui.favorite.movie.MovieFavoriteFragment
import com.example.bajp_submission3.ui.favorite.tvshow.TvShowFavoriteFragment
import com.example.bajp_submission3.utils.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViewPager()

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setViewPager() {
        val listFragment = listOf(MovieFavoriteFragment(), TvShowFavoriteFragment())
        val titleTab = listOf("Movie", "Tv Show")

        binding.viewPager.adapter =
            ViewPagerAdapter(listFragment, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titleTab[position]
        }.attach()
    }

}