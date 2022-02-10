package com.example.bajp_submission3.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.bajp_submission3.data.source.local.LocalDataSource
import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.data.source.remote.ApiResponse
import com.example.bajp_submission3.data.source.remote.RemoteDataSource
import com.example.bajp_submission3.data.source.remote.response.movie.DetailMovieResponse
import com.example.bajp_submission3.data.source.remote.response.movie.MovieResponse
import com.example.bajp_submission3.data.source.remote.response.tvshow.DetailTvShowResponse
import com.example.bajp_submission3.data.source.remote.response.tvshow.TvShowResponse
import com.example.bajp_submission3.utils.AppExecutors
import com.example.bajp_submission3.vo.Resource

class MovieDataRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val listMovie = ArrayList<MovieEntity>()
                for (response in data) {
                    with(response) {
                        val movie = MovieEntity(
                            id,
                            title,
                            description,
                            date,
                            "",
                            score,
                            imagePath,
                            false
                        )

                        listMovie.add(movie)
                    }
                }

                localDataSource.insertMovies(listMovie)
            }
        }.asLiveData()
    }

    override fun getDetailMovies(movieId: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<MovieEntity> =
                localDataSource.getMoviesById(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null && data.genre == ""

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: DetailMovieResponse) {
                with(data) {
                    val genreList = ArrayList<String>()
                    for (i in movieGenres) {
                        genreList.add(i.name)
                    }

                    val detailMovie = MovieEntity(
                        id,
                        originalTitle,
                        overview,
                        releaseDate,
                        genreList.toString(),
                        voteAverage,
                        posterPath,
                        false
                    )
                    localDataSource.insertDetailMovies(detailMovie)
                }
            }

        }.asLiveData()
    }

    override fun getFavoritedMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedMovies(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, newState: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setMovieFavorite(movie, newState)
        }

    // Tv Show
    override fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDb(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShow()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val listTvShow = ArrayList<TvShowEntity>()
                for (response in data) {
                    with(response) {
                        val tvShow = TvShowEntity(
                            id,
                            name,
                            overview,
                            date,
                            "",
                            score,
                            imagePath,
                            false
                        )

                        listTvShow.add(tvShow)
                    }
                }

                localDataSource.insertTvShow(listTvShow)
            }

        }.asLiveData()

    }

    override fun getDetailTvShow(tvShowId: String): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvShowResponse>(appExecutors) {
            override fun loadFromDb(): LiveData<TvShowEntity> =
                localDataSource.getTvShowById(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data != null && data.genre == ""

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> =
                remoteDataSource.getDetailTvShow(tvShowId)

            override fun saveCallResult(data: DetailTvShowResponse) {
                with(data) {
                    val genreList = ArrayList<String>()
                    for (i in genres) {
                        genreList.add(i.name)
                    }

                    val detailTvShow = TvShowEntity(
                        id,
                        originalName,
                        overview,
                        FirstAirDate,
                        genreList.toString(),
                        voteAverage,
                        posterPath,
                        false
                    )
                    localDataSource.insertDetailTvShow(detailTvShow)
                }
            }

        }.asLiveData()
    }

    override fun getFavoritedTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShow(), config).build()
    }

    override fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setTvShowFavorite(tvShow, newState)
        }

    companion object {
        @Volatile
        private var instance: MovieDataRepository? = null

        fun getInstance(
            remote: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieDataRepository =
            instance ?: synchronized(this) {
                instance ?: MovieDataRepository(remote, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

}
