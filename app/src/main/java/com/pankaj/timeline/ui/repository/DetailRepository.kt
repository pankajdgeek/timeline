package com.pankaj.timeline.ui.repository

import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.TimeLineApp
import com.pankaj.timeline.db.AppDatabase
import com.pankaj.timeline.network.Api
import com.pankaj.timeline.network.makeNetworkCall

class DetailRepository(private val api: Api, private val db: AppDatabase) {
    fun getComment(id: Int) = makeNetworkCall {
        api.getPostComments(id)
    }

    fun isPostBookmared(id: Int): Boolean {
        db.postDao().isBookmarkedPost(id).let {
            if (it == null)
                return false
            return true
        }
    }

    suspend fun addFavPost(id: Int, isBookmarked: Boolean): Boolean {
        if (TimeLineApp.isConnectNetwork) {
//                network call
            saveFavPostDB(id, isBookmarked, true)
            return true
        } else {
            saveFavPostDB(id, isBookmarked, false)
            return false
        }

    }


    private suspend fun saveFavPostDB(id: Int, isBookmarked: Boolean, isUploaded: Boolean) {
        printInfoLog(this, " $id $isBookmarked")
        db.postDao().updatePost(id, isBookmarked, isUploaded)
    }
}