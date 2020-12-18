package com.pankaj.timeline.data

sealed class BaseResponse {
    object Loading : BaseResponse()
    data class Error(val error: String?) : BaseResponse()
    data class Success<T>(val data: T) : BaseResponse()
}