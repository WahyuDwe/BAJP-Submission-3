package com.example.bajp_submission3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {

    // Movies
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getMovie()

    fun getFavoritedMovies(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getFavoritedMovie()

    fun getMoviesById(id: String): LiveData<MovieEntity> = mMovieDao.getMoviesById(id)

    fun insertMovies(movies: List<MovieEntity>) = mMovieDao.insertMovie(movies)

    fun insertDetailMovies(movie: MovieEntity) = mMovieDao.insertDetailMovies(movie)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        mMovieDao.updateMovie(movie)
    }

    // Tv Show
    fun getAllTvShow(): DataSource.Factory<Int, TvShowEntity> = mMovieDao.getTvShow()

    fun getFavoritedTvShow(): DataSource.Factory<Int, TvShowEntity> = mMovieDao.getFavoritedTvShow()

    fun getTvShowById(id: String): LiveData<TvShowEntity> = mMovieDao.getTvShowById(id)

    fun insertTvShow(tvShow: List<TvShowEntity>) = mMovieDao.insertTvShow(tvShow)

    fun insertDetailTvShow(tvShow: TvShowEntity) = mMovieDao.insertDetailTvShow(tvShow)

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        mMovieDao.updateTvShow(tvShow)
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: synchronized(this) {
                LocalDataSource(movieDao).apply { INSTANCE = this }
            }
    }
}