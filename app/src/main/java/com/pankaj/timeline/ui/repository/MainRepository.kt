package com.pankaj.timeline.ui.repository

import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.TimeLineApp
import com.pankaj.timeline.data.BaseResponse
import com.pankaj.timeline.data.Post
import com.pankaj.timeline.db.AppDatabase
import com.pankaj.timeline.network.Api
import com.pankaj.timeline.network.makeNetworkCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainRepository(private val api: Api, private val db: AppDatabase) {
    fun getAllPost(): Flow<BaseResponse> {
        if (TimeLineApp.isConnectNetwork) {
            return getPostFromServer()
        } else
            return getPostFromDB()
    }

    fun savePost(list: List<Post>) {
        val ids = arrayListOf<Int>()
        for (post in list) {
            ids.add(post.id)
        }
        val bookmarkedList = db.postDao().postsBookmarked(ids)
        if (bookmarkedList.isNotEmpty()) {
            val bookmaredData = HashMap<Int, Post>()
            for (post in bookmarkedList) {
                bookmaredData.put(post.id, post)
            }
            for (post in list) {
                if (bookmaredData.containsKey(post.id)) {
                    val data = bookmaredData.get(post.id)!!
                    post.isBookmared = data.isBookmared
                    post.isUploaded = data.isUploaded
                }
            }
        }
        db.postDao().insertAll(list)
    }

    private fun getPostFromServer() = makeNetworkCall(api::getPosts)

    private fun getPostFromDB() = flow<BaseResponse> {
        emit(BaseResponse.Success(db.postDao().getAllPost()))
    }

    fun getBookMarkedPost() = flow<BaseResponse> {
        emit(BaseResponse.Success(db.postDao().findBookmaredPost()))
    }


    fun uploadFavList() {
        CoroutineScope(Dispatchers.IO).launch {
            val listPost = db.postDao().findPendingPostUpload()
            if (listPost.isNotEmpty()){
                printInfoLog(this,"uploading fav list to server")
//            upload fav to server
//            onSuccess
//            db.postDao().updateUploadedPost(ids)
            }
        }

    }

}