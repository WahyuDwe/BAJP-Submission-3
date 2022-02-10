package com.example.bajp_submission3.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.vo.Resource

interface MovieDataSource {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovies(movieId: String): LiveData<Resource<MovieEntity>>

    fun getFavoritedMovies(): LiveData<PagedList<MovieEntity>>

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean)

    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailTvShow(tvShowId: String): LiveData<Resource<TvShowEntity>>

    fun getFavoritedTvShow(): LiveData<PagedList<TvShowEntity>>

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean)
}