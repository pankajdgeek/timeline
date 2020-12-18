package com.pankaj.timeline.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.google.android.material.snackbar.Snackbar
import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.R
import com.pankaj.timeline.databinding.ActivityDetailBinding
import com.pankaj.timeline.ui.adapter.CommentAdapter
import com.pankaj.timeline.viewmodel.DetailViewModel
import com.pankaj.timeline.util.UploadWorker
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class DetailActivity : AppCompatActivity() {
    val viewModel: DetailViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.viewModel = viewModel
        initializeObservers()
        intent.extras?.let {
            viewModel.title.set(it.getString("title") ?: "")
            viewModel.body.set(it.getString("body") ?: "")
            viewModel.getComments(it.getInt("id"))
        }
    }

    private fun setAdapter(adapter: CommentAdapter) {
        rv_comment.layoutManager = LinearLayoutManager(this)
        rv_comment.adapter = adapter
    }

    private fun initializeObservers() {
        viewModel.commentList.observe(this, {
            printInfoLog(this, " get the resutl $it")
            setAdapter(CommentAdapter(it))
        })
        viewModel.errorMessage.observe(this, {
            Snackbar.make(root, it, Snackbar.LENGTH_LONG).show()
        })
        viewModel.saveUploadRequest.observe(this, {
            saveUploadRequest()
        })
    }

    private fun saveUploadRequest() {
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val request: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<UploadWorker>()
                .setConstraints(constraints)
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 20, TimeUnit.SECONDS)
                .build()

        WorkManager.getInstance(this)
            .enqueue(request)

    }
}
