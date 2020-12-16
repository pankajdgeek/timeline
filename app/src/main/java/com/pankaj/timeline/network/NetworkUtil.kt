package com.pankaj.timeline.network

import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.data.BaseResponse
import com.pankaj.timeline.data.Post
import kotlinx.coroutines.flow.flow
import kotlin.reflect.KSuspendFunction0

fun  <T>makeNetworkCall(call: KSuspendFunction0<T>) =  flow  {
    emit(BaseResponse.Loading)
    try {
        emit(BaseResponse.Success(call.invoke()))
    } catch (e: Exception) {
        emit(BaseResponse.Error(e.message))
    }
}