package com.pankaj.timeline.ui.repository

import com.pankaj.pankaj.util.printInfoLog
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

    fun saveFavPostDB(id: Int, isBookmarked: Boolean, isUploaded: Boolean) {
        printInfoLog(this, " $id $isBookmarked $isUploaded")
        db.postDao().updatePost(postId = id, bookmark = isBookmarked, upload = isUploaded)

    }
}