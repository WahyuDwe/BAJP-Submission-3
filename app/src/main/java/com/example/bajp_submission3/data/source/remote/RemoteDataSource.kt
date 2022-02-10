package com.example.bajp_submission3.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bajp_submission3.data.source.remote.response.movie.DetailMovieResponse
import com.example.bajp_submission3.data.source.remote.response.movie.MovieContentResponse
import com.example.bajp_submission3.data.source.remote.response.movie.MovieResponse
import com.example.bajp_submission3.data.source.remote.response.tvshow.DetailTvShowResponse
import com.example.bajp_submission3.data.source.remote.response.tvshow.TvShowContentResponse
import com.example.bajp_submission3.data.source.remote.response.tvshow.TvShowResponse
import com.example.bajp_submission3.network.ApiConfig
import com.example.bajp_submission3.utils.EspressoIdlingResources
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource {

    companion object {
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource()
        }
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResources.increment()
        val client = ApiConfig.getService().getMovies()
        val resultMovies = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        client.enqueue(object : Callback<MovieContentResponse> {
            override fun onResponse(
                call: Call<MovieContentResponse>,
                response: Response<MovieContentResponse>
            ) {
                resultMovies.value =
                    ApiResponse.success(response.body()?.results as List<MovieResponse>)
                Log.d(TAG, "onResponse : ${response.body()}")
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<MovieContentResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
                EspressoIdlingResources.decrement()
            }
        })
        return resultMovies
    }

    fun getDetailMovie(movieId: String): LiveData<ApiResponse<DetailMovieResponse>> {
        EspressoIdlingResources.increment()
        val client = ApiConfig.getService().getDetailMovie(movieId)
        val resultDetailMovies = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        client.enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                resultDetailMovies.value =
                    ApiResponse.success(response.body() as DetailMovieResponse)
                Log.d(TAG, "onResponse : ${response.body()}")
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
                EspressoIdlingResources.decrement()
            }
        })
        return resultDetailMovies
    }

    fun getTvShow(): LiveData<ApiResponse<List<TvShowResponse>>> {
        EspressoIdlingResources.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        val client = ApiConfig.getService().getTvShow()
        client.enqueue(object : Callback<TvShowContentResponse> {
            override fun onResponse(
                call: Call<TvShowContentResponse>,
                response: Response<TvShowContentResponse>
            ) {
                resultTvShow.value =
                    ApiResponse.success(response.body()?.results as List<TvShowResponse>)
                Log.d(TAG, "onResponse : ${response.body()}")
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<TvShowContentResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
                EspressoIdlingResources.decrement()
            }

        })
        return resultTvShow
    }

    fun getDetailTvShow(tvShowId: String): LiveData<ApiResponse<DetailTvShowResponse>> {
        EspressoIdlingResources.increment()
        val resultDetailTvShow = MutableLiveData<ApiResponse<DetailTvShowResponse>>()
        val client = ApiConfig.getService().getDetailTvShow(tvShowId)
        client.enqueue(object : Callback<DetailTvShowResponse> {
            override fun onResponse(
                call: Call<DetailTvShowResponse>,
                response: Response<DetailTvShowResponse>
            ) {
                resultDetailTvShow.value = response.body()?.let { ApiResponse.success(it) }
                Log.d(TAG, "onResponse : ${response.body()}")
                EspressoIdlingResources.decrement()
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.d("Failure", t.message!!)
                EspressoIdlingResources.decrement()
            }
        })
        return resultDetailTvShow
    }

}