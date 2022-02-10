package com.example.bajp_submission3.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.bajp_submission3.data.MovieDataRepository
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.vo.Resource

class TvShowViewModel(private val movieDataRepository: MovieDataRepository) : ViewModel() {
    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = movieDataRepository.getTvShow()
}