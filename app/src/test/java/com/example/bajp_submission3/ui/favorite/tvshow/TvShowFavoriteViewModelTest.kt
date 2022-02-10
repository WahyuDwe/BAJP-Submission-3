package com.example.bajp_submission3.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.bajp_submission3.data.MovieDataRepository
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
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
class TvShowFavoriteViewModelTest {

    private lateinit var viewModel: TvShowFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDataRepository: MovieDataRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowFavoriteViewModel(movieDataRepository)
    }

    @Test
    fun getFavTvShow() {
        val dummyTvShow = pagedList
        `when`(dummyTvShow.size).thenReturn(3)
        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(movieDataRepository.getFavoritedTvShow()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getFavTvShow().value
        verify(movieDataRepository).getFavoritedTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(3, tvShowEntities?.size)

        viewModel.getFavTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}