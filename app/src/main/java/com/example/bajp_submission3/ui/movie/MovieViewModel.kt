package com.example.bajp_submission3.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.bajp_submission3.data.MovieDataRepository
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.vo.Resource

class MovieViewModel(private val movieDataRepository: MovieDataRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieDataRepository.getMovies()
}