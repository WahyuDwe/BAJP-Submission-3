package com.example.bajp_submission3.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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
    }

    private fun setViewPager() {
        val listFragment = listOf(MovieFragment(), TvShowFragment())
        val titleTab = listOf(getString(R.string.movie_tablayout), getString(R.string.tvshow_tablayout))

        binding.viewPager.adapter =
            ViewPagerAdapter(listFragment, this.supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titleTab[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.main_favorite) {
            Intent(this, FavoriteActivity::class.java).also {
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}