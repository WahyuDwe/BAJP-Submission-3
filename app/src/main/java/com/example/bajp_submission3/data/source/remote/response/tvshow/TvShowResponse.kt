package com.example.bajp_submission3.data.source.remote.response.tvshow

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
    @SerializedName("id")
    var id: Int,

    @SerializedName("original_name")
    var name: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("first_air_date")
    var date: String,

    @SerializedName("vote_average")
    var score: Double,

    @SerializedName("poster_path")
    var imagePath: String
) : Parcelable
