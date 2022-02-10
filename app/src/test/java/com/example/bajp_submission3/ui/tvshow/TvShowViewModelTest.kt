package com.example.bajp_submission3.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.bajp_submission3.data.MovieDataRepository
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.data.source.remote.response.tvshow.TvShowResponse
import com.example.bajp_submission3.utils.DataDummy
import com.example.bajp_submission3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDataRepository: MovieDataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieDataRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTv = Resource.success(pagedList)
        `when`(dummyTv.data?.size).thenReturn(3)
        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTv

        `when`(movieDataRepository.getTvShow()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getTvShow().value?.data
        verify(movieDataRepository).getTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(3, tvShowEntities?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}