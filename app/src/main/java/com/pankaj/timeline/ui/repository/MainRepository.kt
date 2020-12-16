package com.pankaj.timeline.ui.repository

import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.data.BaseResponse
import com.pankaj.timeline.data.Post
import com.pankaj.timeline.db.AppDatabase
import com.pankaj.timeline.network.Api
import com.pankaj.timeline.network.makeNetworkCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class MainRepository(private val api: Api,private val db:AppDatabase) {
    fun getAllPost(): Flow<BaseResponse> {
        return getPostComments()//getPostFromServer()
    }

    fun getPostComments() = flow<BaseResponse>{
        printInfoLog(this,"called all posts")
        emit(BaseResponse.Success(db.postDao().getAllPost()))
    }
    fun savePost(list:List<Post>){
       db.postDao().insertAll(list)
    }

    private fun getPostFromServer() = makeNetworkCall(api::getPosts)

    private fun getPostFromDB() {

    }

    fun getBookMarkedPost() {

    }

    fun uploadBookMaredPost() {

    }
}