package com.example.bajp_submission3.data.source.remote.response.movie

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("id")
    var id: Int,

    @SerializedName("original_title")
    var title: String,

    @SerializedName("overview")
    var description: String,

    @SerializedName("release_date")
    var date: String,

    @SerializedName("vote_average")
    var score: Double,

    @SerializedName("poster_path")
    var imagePath: String
) : Parcelable
