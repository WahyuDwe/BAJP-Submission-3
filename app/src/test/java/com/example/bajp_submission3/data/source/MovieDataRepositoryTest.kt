package com.example.bajp_submission3.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.bajp_submission3.data.source.local.LocalDataSource
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.data.source.remote.RemoteDataSource
import com.example.bajp_submission3.utils.AppExecutors
import com.example.bajp_submission3.utils.DataDummy
import com.example.bajp_submission3.utils.LiveDataTestUtil
import com.example.bajp_submission3.utils.PagedListUtil
import com.example.bajp_submission3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieDataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieDataRepository = FakeMovieDataRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.dataDummyRemoteMovie()
    private val movieId = movieResponse[0].id.toString()
    private val movieDetail = DataDummy.dataDummyRemoteDetailMovie()

    private val tvShowResponse = DataDummy.dataDummyRemoteTvShow()
    private val tvShowId = tvShowResponse[0].id.toString()
    private val tvShowDetail = DataDummy.dataDummyRemoteDetailTvShow()

    @Test
    fun getMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        movieDataRepository.getMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.dataDummyMovies()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovies() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataDummy.dataDummyDetailMovies()
        `when`(local.getMoviesById(movieId)).thenReturn(dummyDetail)

        val detailMovieEntities =
            LiveDataTestUtil.getValue(movieDataRepository.getDetailMovies(movieId))
        verify(local).getMoviesById(movieId)
        assertNotNull(detailMovieEntities)
        assertEquals(movieDetail.id, detailMovieEntities.data?.id)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoritedMovies()).thenReturn(dataSourceFactory)
        movieDataRepository.getFavoritedMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.dataDummyMovies()))
        verify(local).getFavoritedMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun setFavoriteMovie() {
        movieDataRepository.setMovieFavorite(DataDummy.dataDummyDetailMovies(), true)
        verify(local).setMovieFavorite(DataDummy.dataDummyDetailMovies(), true)
        verifyNoMoreInteractions(local)
    }

    @Test
    fun getTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        movieDataRepository.getTvShow()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.dataDummyTvShow()))
        verify(local).getAllTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailTvShow() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataDummy.dataDummyDetailTvShow()
        `when`(local.getTvShowById(tvShowId)).thenReturn(dummyDetail)

        val detailTvShowEntities =
            LiveDataTestUtil.getValue(movieDataRepository.getDetailTvShow(tvShowId))
        verify(local).getTvShowById(tvShowId)
        assertNotNull(detailTvShowEntities)
        assertEquals(tvShowDetail.id, detailTvShowEntities.data?.id)
    }

    @Test
    fun getFavoriteTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoritedTvShow()).thenReturn(dataSourceFactory)
        movieDataRepository.getFavoritedTvShow()

        val tvShowEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.dataDummyTvShow()))
        verify(local).getFavoritedTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun setFavoriteTvShow() {
        movieDataRepository.setTvShowFavorite(DataDummy.dataDummyDetailTvShow(), true)
        verify(local).setTvShowFavorite(DataDummy.dataDummyDetailTvShow(), true)
        verifyNoMoreInteractions(local)
    }
}