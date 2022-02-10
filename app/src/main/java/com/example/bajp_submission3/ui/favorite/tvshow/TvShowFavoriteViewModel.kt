package com.example.bajp_submission3.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import com.example.bajp_submission3.data.MovieDataRepository

class TvShowFavoriteViewModel(private val repository: MovieDataRepository) : ViewModel() {
    fun getFavTvShow() = repository.getFavoritedTvShow()

}