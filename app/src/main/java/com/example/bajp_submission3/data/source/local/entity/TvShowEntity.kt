package com.example.bajp_submission3.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_tvshow")
    var id: Int,

    @ColumnInfo(name = "title_tvshow")
    var title: String,

    @ColumnInfo(name = "description_tvshow")
    var description: String,

    @ColumnInfo(name = "date_tvshow")
    var date: String,

    @ColumnInfo(name = "genre_tvshow")
    var genre: String,

    @ColumnInfo(name = "score_tvshow")
    var score: Double,

    @ColumnInfo(name = "imagepath_tvshow")
    var imagePath: String?,

    @ColumnInfo(name = "favorited_tvshow")
    var favorited: Boolean = false
)
