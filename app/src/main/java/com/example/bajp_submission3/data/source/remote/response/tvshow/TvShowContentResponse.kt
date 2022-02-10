package com.example.bajp_submission3.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowContentResponse(
    @SerializedName("results")
    val results: List<TvShowResponse>
)

