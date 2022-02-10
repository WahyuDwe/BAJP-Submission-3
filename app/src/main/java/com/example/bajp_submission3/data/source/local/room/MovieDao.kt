package com.example.bajp_submission3.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {

    // Movie
    @Query("SELECT * FROM movieentities")
    fun getMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE favorited_movie = 1")
    fun getFavoritedMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE id_movie = :id")
    fun getMoviesById(id: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovies(movies: MovieEntity)

    @Update
    fun updateMovie(movies: MovieEntity)

    // Tv Show
    @Query("SELECT * FROM tvshowentities")
    fun getTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE favorited_tvshow = 1")
    fun getFavoritedTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE id_tvshow = :id")
    fun getTvShowById(id: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTvShow(tvShow: TvShowEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}
