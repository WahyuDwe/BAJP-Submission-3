package com.example.bajp_submission3.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.example.bajp_submission3.data.MovieDataRepository
import com.example.bajp_submission3.data.source.local.entity.MovieEntity

class MovieFavoriteViewModel(private val repository: MovieDataRepository) : ViewModel() {
    fun getFavMovies() = repository.getFavoritedMovies()
}