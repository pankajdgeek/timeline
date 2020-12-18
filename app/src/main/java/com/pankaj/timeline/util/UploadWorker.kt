package com.pankaj.timeline.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams), KoinComponent {
    val db: AppDatabase by inject()
    override fun doWork(): Result {
        uploadFavList(db)
        // Do the work here--in this case, upload the images.

        printInfoLog(this, "Upload fav files here")

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }

    fun uploadFavList(db: AppDatabase) {
        CoroutineScope(Dispatchers.IO).launch {
            val listPost = db.postDao().findPendingPostUpload()
            if (listPost.isNotEmpty()) {
                printInfoLog(this, "uploading fav list to server")
//            upload fav to server
//            onSuccess
//            db.postDao().updateUploadedPost(ids)
            }
        }

    }
}
