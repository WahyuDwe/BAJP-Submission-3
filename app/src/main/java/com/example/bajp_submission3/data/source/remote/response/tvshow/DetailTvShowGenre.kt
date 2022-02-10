package com.example.bajp_submission3.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class DetailTvShowGenre(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)
