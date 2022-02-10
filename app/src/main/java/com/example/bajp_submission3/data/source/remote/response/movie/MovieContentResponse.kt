package com.example.bajp_submission3.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieContentResponse(
    @SerializedName("results")
    val results: List<MovieResponse>
)

