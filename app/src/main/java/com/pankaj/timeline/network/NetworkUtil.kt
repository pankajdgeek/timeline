package com.pankaj.timeline.network

import com.pankaj.timeline.TimeLineApp
import com.pankaj.timeline.data.BaseResponse
import kotlinx.coroutines.flow.flow

inline fun <T> makeNetworkCall(crossinline call: suspend () -> T) = flow {
    if (!TimeLineApp.isConnectNetwork) {
        emit(BaseResponse.Error("Check your internet"))
        return@flow
    }
    emit(BaseResponse.Loading)
    try {
        emit(BaseResponse.Success(call.invoke()))
    } catch (e: Exception) {
        emit(BaseResponse.Error(e.message))
    }
}