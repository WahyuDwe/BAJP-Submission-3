package com.example.bajp_submission3.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.bajp_submission3.data.MovieDataRepository
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.ui.detail.DetailViewModel.Companion.MOVIE
import com.example.bajp_submission3.ui.detail.DetailViewModel.Companion.TV_SHOW
import com.example.bajp_submission3.utils.DataDummy
import com.example.bajp_submission3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.atLeast
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dataDummyDetailMovies = DataDummy.dataDummyDetailMovies()
    private val movieId = dataDummyDetailMovies.id.toString()

    private val dataDummyDetailTvShow = DataDummy.dataDummyDetailTvShow()
    private val tvShowId = dataDummyDetailTvShow.id.toString()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDataRepository: MovieDataRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>

    // Testing for detail Movie
    @Before
    fun setUpMovies() {
        viewModel = DetailViewModel(movieDataRepository)
    }

    @Test
    fun getContentDetailMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.dataDummyDetailMovies())
        val movieDetail = MutableLiveData<Resource<MovieEntity>>()
        movieDetail.value = dummyDetailMovie

        movieId.let { viewModel.setContent(it, MOVIE) }
        `when`(movieId.let { movieDataRepository.getDetailMovies(it) }).thenReturn(movieDetail)
        val resultMovie = viewModel.getDetailMovie().value?.data

        movieId.let { verify(movieDataRepository, atLeast(1)).getDetailMovies(it) }
        assertNotNull(resultMovie)

        assertEquals(dataDummyDetailMovies.id, resultMovie?.id)
        assertEquals(dataDummyDetailMovies.title, resultMovie?.title)
        assertEquals(dataDummyDetailMovies.date, resultMovie?.date)
        assertEquals(dataDummyDetailMovies.imagePath, resultMovie?.imagePath)
        assertEquals(dataDummyDetailMovies.favorited, resultMovie?.favorited)
        assertEquals(dataDummyDetailMovies.description, resultMovie?.description)
        assertEquals(dataDummyDetailMovies.score, resultMovie?.score)
        assertEquals(dataDummyDetailMovies.genre, resultMovie?.genre)
        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyDetailMovie = Resource.success(DataDummy.dataDummyDetailMovies())
        val movieDetail = MutableLiveData<Resource<MovieEntity>>()
        movieDetail.value = dummyDetailMovie

        `when`(movieDataRepository.getDetailMovies(movieId)).thenReturn(movieDetail)

        viewModel.setContent(movieId, MOVIE)
        viewModel.setFavoriteMovie()
        verify(movieDataRepository).setMovieFavorite(movieDetail.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    // Testing for detail Tv Show
    @Before
    fun setUpTvShow() {
        viewModel = DetailViewModel(movieDataRepository)
    }

    @Test
    fun getContentDetailTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.dataDummyDetailTvShow())
        val tvShowDetail = MutableLiveData<Resource<TvShowEntity>>()
        tvShowDetail.value = dummyDetailTvShow
        tvShowId.let { viewModel.setContent(it, TV_SHOW) }

        `when`(tvShowId.let { movieDataRepository.getDetailTvShow(it) }).thenReturn(tvShowDetail)
        val resultTvShow = viewModel.getDetailTvShow().value?.data

        tvShowId.let { verify(movieDataRepository, atLeast(1)).getDetailTvShow(it) }
        assertNotNull(resultTvShow)

        assertEquals(dataDummyDetailTvShow.id, resultTvShow?.id)
        assertEquals(dataDummyDetailTvShow.title, resultTvShow?.title)
        assertEquals(dataDummyDetailTvShow.date, resultTvShow?.date)
        assertEquals(dataDummyDetailTvShow.imagePath, resultTvShow?.imagePath)
        assertEquals(dataDummyDetailTvShow.favorited, resultTvShow?.favorited)
        assertEquals(dataDummyDetailTvShow.description, resultTvShow?.description)
        assertEquals(dataDummyDetailTvShow.score, resultTvShow?.score)
        assertEquals(dataDummyDetailTvShow.genre, resultTvShow?.genre)
        viewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyDetailTvShow = Resource.success(DataDummy.dataDummyDetailTvShow())
        val tvShowDetail = MutableLiveData<Resource<TvShowEntity>>()
        tvShowDetail.value = dummyDetailTvShow

        `when`(movieDataRepository.getDetailTvShow(tvShowId)).thenReturn(tvShowDetail)

        viewModel.setContent(tvShowId, TV_SHOW)
        viewModel.setFavoriteTvShow()
        verify(movieDataRepository).setTvShowFavorite(
            tvShowDetail.value!!.data as TvShowEntity,
            true
        )
    }
}