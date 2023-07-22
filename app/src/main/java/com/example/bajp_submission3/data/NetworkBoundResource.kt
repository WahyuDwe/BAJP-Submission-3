package com.example.bajp_submission3.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.bajp_submission3.data.source.remote.ApiResponse
import com.example.bajp_submission3.data.source.remote.StatusResponse
import com.example.bajp_submission3.utils.AppExecutors
import com.example.bajp_submission3.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutor: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        val dbSource = this.loadFromDb()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDb(): LiveData<ResultType>

    abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData)
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS -> mExecutor.diskIO().execute {
                    saveCallResult(response.body)
                    mExecutor.mainThread().execute {
                        result.addSource(loadFromDb()) { newData ->
                            result.value = Resource.success(newData)
                        }
                    }
                }

                StatusResponse.EMPTY -> mExecutor.mainThread().execute {
                    result.addSource(loadFromDb()) { newData ->
                        result.value = Resource.success(newData)
                    }
                }

                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}