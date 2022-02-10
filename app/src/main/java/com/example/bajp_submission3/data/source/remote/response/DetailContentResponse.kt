package com.example.bajp_submission3.data.source.remote.response

import com.example.bajp_submission3.data.source.remote.response.movie.DetailMovieGenre
import com.google.gson.annotations.SerializedName

data class DetailContentResponse(
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

    // Title and Date For Tv Show
    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("first_air_date")
    var FirstAirDate: String,

    // Get Genre
    @SerializedName("genres")
    val genres: List<DetailMovieGenre>
)
