package com.pankaj.timeline.ui.repository

import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.db.AppDatabase
import com.pankaj.timeline.network.Api
import com.pankaj.timeline.network.makeNetworkCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    fun addFavPost(id: Int, isBookmarked: Boolean) {
//      network call if success make isUpladed = true
        CoroutineScope(Dispatchers.IO).launch {
            saveFavPostDB(id, isBookmarked)
        }
    }

    private fun saveFavPostDB(id: Int, isBookmarked: Boolean) {
        printInfoLog(this, " $id $isBookmarked")
        db.postDao().updatePost(id, isBookmarked)
    }
}