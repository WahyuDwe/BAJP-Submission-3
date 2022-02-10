package com.example.bajp_submission3.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bajp_submission3.data.MovieDataRepository
import com.example.bajp_submission3.di.Injection
import com.example.bajp_submission3.ui.detail.DetailMovieViewModel
import com.example.bajp_submission3.ui.favorite.movie.MovieFavoriteViewModel
import com.example.bajp_submission3.ui.favorite.tvshow.TvShowFavoriteViewModel
import com.example.bajp_submission3.ui.movie.MovieViewModel
import com.example.bajp_submission3.ui.tvshow.TvShowViewModel


class ViewModelFactory private constructor(private val movieDataRepository: MovieDataRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieDataRepository) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(movieDataRepository) as T
            }

            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(movieDataRepository) as T
            }

            modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java) -> {
                MovieFavoriteViewModel(movieDataRepository) as T
            }

            modelClass.isAssignableFrom(TvShowFavoriteViewModel::class.java) -> {
                TvShowFavoriteViewModel(movieDataRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }
}