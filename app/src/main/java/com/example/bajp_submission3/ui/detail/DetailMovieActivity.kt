package com.example.bajp_submission3.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.bajp_submission3.BuildConfig
import com.example.bajp_submission3.R
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.databinding.ActivityDetailMovieBinding
import com.example.bajp_submission3.ui.detail.DetailMovieViewModel.Companion.MOVIE
import com.example.bajp_submission3.ui.detail.DetailMovieViewModel.Companion.TV_SHOW
import com.example.bajp_submission3.viewmodel.ViewModelFactory
import com.example.bajp_submission3.vo.Status
import com.google.android.material.appbar.AppBarLayout


class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding

    private var isShow = true
    private var scrollRange = -1
    private var contentCategory: String? = null

    private var menu: Menu? = null
    private lateinit var viewModel: DetailMovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        supportActionBar?.hide()
        showProgressBar(true)
        activityDetailMovieBinding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
        activityDetailMovieBinding.fabFavorite.setOnClickListener(this)


        val extras = intent.extras
        if (extras != null) {
            val contentId = extras.getString(EXTRA_MOVIE)
            contentCategory = extras.getString(EXTRA_CATEGORY)

            if (contentId != null && contentCategory != null) {
                viewModel.setContent(contentId, contentCategory.toString())
                stateSetup()
                if (contentCategory == MOVIE) {
                    viewModel.getDetailMovie().observe(this) { detailMovie ->
                        when (detailMovie.status) {
                            Status.LOADING -> showProgressBar(true)

                            Status.SUCCESS -> {
                                if (detailMovie.data != null) {
                                    showProgressBar(false)
                                    populateContentDetail(detailMovie.data)
                                }
                            }

                            Status.ERROR -> {
                                showProgressBar(false)
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi Kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                } else if (contentCategory == TV_SHOW) {
                    viewModel.getDetailTvShow().observe(this) { detailTv ->
                        when (detailTv.status) {
                            Status.LOADING -> showProgressBar(true)

                            Status.SUCCESS -> {
                                if (detailTv.data != null) {
                                    showProgressBar(false)
                                    populateContentDetail(detailTv.data)
                                }
                            }

                            Status.ERROR -> {
                                showProgressBar(false)
                                Toast.makeText(
                                    applicationContext,
                                    "Terjadi Kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }

            if (extras.getString(EXTRA_CATEGORY) == MOVIE) {
                showTitleCollapse("Detail Movie")
            } else {
                showTitleCollapse("Detail Tv Show")
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

    private fun showProgressBar(state: Boolean) {
        activityDetailMovieBinding.progressBar.isVisible = state
        activityDetailMovieBinding.appbar.isGone = state
        activityDetailMovieBinding.nestedScrollView.isGone = state
        activityDetailMovieBinding.fabFavorite.isGone = state
    }

    @JvmName("ContentDetailMovie")
    private fun populateContentDetail(content: MovieEntity) {
        activityDetailMovieBinding.detailTitle.text = content.title
        activityDetailMovieBinding.detailGenre.text =
            content.genre.replace("[", "").replace("]", "")
        activityDetailMovieBinding.detailsReleaseDate.text = content.date
        activityDetailMovieBinding.detailDescription.text = content.description
        activityDetailMovieBinding.detailScore.text = content.score.toString()

        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + content.imagePath)
            .into(activityDetailMovieBinding.ivDetailToolbar)


        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + content.imagePath)
            .into(activityDetailMovieBinding.detailPoster)
    }

    @JvmName("ContentDetailTvShow")
    private fun populateContentDetail(content: TvShowEntity) {
        activityDetailMovieBinding.detailTitle.text = content.title
        activityDetailMovieBinding.detailGenre.text =
            content.genre.replace("[", "").replace("]", "")
        activityDetailMovieBinding.detailsReleaseDate.text = content.date
        activityDetailMovieBinding.detailDescription.text = content.description
        activityDetailMovieBinding.detailScore.text = content.score.toString()

        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + content.imagePath)
            .into(activityDetailMovieBinding.ivDetailToolbar)


        Glide.with(this)
            .load(BuildConfig.IMAGE_URL + content.imagePath)
            .into(activityDetailMovieBinding.detailPoster)
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



    private fun stateSetup() {
        if (contentCategory == MOVIE) {
            viewModel.getDetailMovie().observe(this) { movie ->
                when (movie.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            showProgressBar(false)
                            val state = movie.data.favorited
                            setFavoriteState(state)
                        }
                    }

                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Terjadi Kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        } else if (contentCategory == TV_SHOW) {
            viewModel.getDetailTvShow().observe(this) { tvShow ->
                when (tvShow.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            showProgressBar(false)
                            val state = tvShow.data.favorited
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(applicationContext, "Terjadi Kesalahan", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
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
        if (state) {
            fab.setImageResource(R.drawable.ic_favorited)
        } else {
            fab.setImageResource(R.drawable.ic_favorite)
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_CATEGORY = "extra_category"
    }

}