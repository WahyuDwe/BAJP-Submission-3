package com.example.bajp_submission3.network

import com.example.bajp_submission3.BuildConfig
import com.example.bajp_submission3.data.source.remote.response.movie.DetailMovieResponse
import com.example.bajp_submission3.data.source.remote.response.movie.MovieContentResponse
import com.example.bajp_submission3.data.source.remote.response.tvshow.DetailTvShowResponse
import com.example.bajp_submission3.data.source.remote.response.tvshow.TvShowContentResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieContentResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<DetailMovieResponse>

    @GET("tv/popular")
    fun getTvShow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<TvShowContentResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<DetailTvShowResponse>
}