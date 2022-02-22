package com.example.bajp_submission3.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.bajp_submission3.R
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.databinding.ActivityDetailMovieBinding
import com.example.bajp_submission3.ui.detail.DetailViewModel.Companion.MOVIE
import com.example.bajp_submission3.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.example.bajp_submission3.utils.SetImage
import com.example.bajp_submission3.viewmodel.ViewModelFactory
import com.example.bajp_submission3.vo.Resource
import com.example.bajp_submission3.vo.Status
import com.google.android.material.appbar.AppBarLayout


class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding

    private var isShow = true
    private var scrollRange = -1
    private var contentCategory: String? = null

    private lateinit var viewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        supportActionBar?.hide()
        showProgressBar(true)
        activityDetailMovieBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        activityDetailMovieBinding.fabFavorite.setOnClickListener(this)


        val extras = intent.extras
        if (extras != null) {
            val contentId = extras.getString(EXTRA_MOVIE)
            contentCategory = extras.getString(EXTRA_CATEGORY)

            if (contentId != null && contentCategory != null) {
                viewModel.setContent(contentId, contentCategory.toString())

                if (contentCategory == MOVIE) {
                    viewModel.getDetailMovie().observe(this, ::showDetail)
                } else if (contentCategory == TV_SHOW) {
                    viewModel.getDetailTvShow().observe(this, ::showDetail)
                }
            }
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_favorite -> {
                onFabClicked()
            }
        }
    }

    private fun <T> showDetail(resource: Resource<T>) {
        when (resource.status) {
            Status.LOADING -> showProgressBar(true)

            Status.SUCCESS -> {
                if (resource.data != null) {
                    showProgressBar(false)
                    val data = resource.data
                    if (data is MovieEntity) {
                        populateContentDetail(data)
                        val state = data.favorited
                        setFavoriteState(state)
                        showTitleCollapse("Detail Movie")
                    }

                    if (data is TvShowEntity) {
                        populateContentDetail(data)
                        val state = data.favorited
                        setFavoriteState(state)
                        showTitleCollapse("Detail Tv Show")
                    }
                }
            }

            Status.ERROR -> {
                showProgressBar(false)
                Toast.makeText(applicationContext, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        activityDetailMovieBinding.apply {
            progressBar.isVisible = state
            appbar.isGone = state
            nestedScrollView.isGone = state
            fabFavorite.isGone = state
        }
    }

    @JvmName("ContentDetailMovie")
    private fun populateContentDetail(content: MovieEntity) {
        activityDetailMovieBinding.apply {
            detailTitle.text = content.title
            detailGenre.text = content.genre.replace("[", "").replace("]", "")
            detailsReleaseDate.text = content.date
            detailDescription.text = content.description
            detailScore.text = content.score.toString()
            SetImage.loadImage(ivDetailToolbar, content.imagePath)
            SetImage.loadImage(detailPoster, content.imagePath)
        }
    }

    @JvmName("ContentDetailTvShow")
    private fun populateContentDetail(content: TvShowEntity) {
        activityDetailMovieBinding.apply {
            detailTitle.text = content.title
            detailGenre.text = content.genre.replace("[", "").replace("]", "")
            detailsReleaseDate.text = content.date
            detailDescription.text = content.description
            detailScore.text = content.score.toString()
            SetImage.loadImage(ivDetailToolbar, content.imagePath)
            SetImage.loadImage(detailPoster, content.imagePath)
        }
    }

    private fun showTitleCollapse(statusBar: String) {
        activityDetailMovieBinding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                activityDetailMovieBinding.collapsingToolbar.title = statusBar

                isShow = true
            } else if (isShow) {
                activityDetailMovieBinding.collapsingToolbar.title = " "
                isShow = false
            }
        })
    }

    private fun onFabClicked() {
        if (contentCategory == MOVIE) {
            viewModel.setFavoriteMovie()
        } else if (contentCategory == TV_SHOW) {
            viewModel.setFavoriteTvShow()
        }
    }

    private fun setFavoriteState(state: Boolean) {
        val fab = activityDetailMovieBinding.fabFavorite
        fab.setImageResource(
            if (state) {
                R.drawable.ic_favorited
            } else {
                R.drawable.ic_favorite
            }
        )
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_CATEGORY = "extra_category"
    }

}