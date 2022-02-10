package com.example.bajp_submission3.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class DetailTvShowResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("first_air_date")
    var FirstAirDate: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("genres")
    val genres: List<DetailTvShowGenre>
)
