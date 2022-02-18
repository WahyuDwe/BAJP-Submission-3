package com.example.bajp_submission3.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movieentities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_movie")
    var id: Int,

    @ColumnInfo(name = "title_movie")
    var title: String,

    @ColumnInfo(name = "description_movie")
    var description: String,

    @ColumnInfo(name = "date_movie")
    var date: String,

    @ColumnInfo(name = "genre_movie")
    var genre: String,

    @ColumnInfo(name = "score_movie")
    var score: Double,

    @ColumnInfo(name = "imagepath_info")
    var imagePath: String,

    @ColumnInfo(name = "favorited_movie")
    var favorited: Boolean = false
)
