package com.example.bajp_submission3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bajp_submission3.data.MovieDataRepository
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.vo.Resource

class DetailViewModel(private val movieDataRepository: MovieDataRepository) : ViewModel() {
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>
    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>
    private var id: String = ""

    fun setContent(id: String, category: String) {
        when (category) {
            TV_SHOW -> {
                detailTvShow = movieDataRepository.getDetailTvShow(id)
            }

            MOVIE -> {
                detailMovie = movieDataRepository.getDetailMovies(id)
            }
        }
        this.id = id
    }


    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.favorited
            movieDataRepository.setMovieFavorite(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value
        if (resource?.data != null) {
            val newState = !resource.data.favorited
            movieDataRepository.setTvShowFavorite(resource.data, newState)
        }
    }

    fun getDetailMovie() = movieDataRepository.getDetailMovies(id).also { detailMovie = it }


    fun getDetailTvShow() = movieDataRepository.getDetailTvShow(id).also { detailTvShow = it }

    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tv_show"
    }
}



