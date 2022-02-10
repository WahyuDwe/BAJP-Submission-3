package com.example.bajp_submission3.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class DetailMovieGenre(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)
