package com.example.bajp_submission3.di

import android.content.Context
import com.example.bajp_submission3.data.MovieDataRepository
import com.example.bajp_submission3.data.source.local.LocalDataSource
import com.example.bajp_submission3.data.source.local.room.MovieDatabase
import com.example.bajp_submission3.data.source.remote.RemoteDataSource
import com.example.bajp_submission3.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieDataRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieDataRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}