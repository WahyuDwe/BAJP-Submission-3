package com.example.bajp_submission3.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("genres")
    val movieGenres: List<DetailMovieGenre>
)
