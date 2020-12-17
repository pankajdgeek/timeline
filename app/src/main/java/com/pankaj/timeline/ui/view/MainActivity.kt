package com.pankaj.timeline.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pankaj.timeline.R
import com.pankaj.timeline.TimeLineApp
import com.pankaj.timeline.databinding.ActivityMainBinding
import com.pankaj.timeline.ui.adapter.PostAdapter
import com.pankaj.timeline.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_layout.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.isLoading = viewModel.isLoading
        initializeObservers()
        viewModel.getPost()
    }

    private fun setAdapter(adapter: PostAdapter) {
        rv_post.layoutManager = LinearLayoutManager(this)
        rv_post.adapter = adapter
    }

    override fun onResume() {
        if (viewModel.navigationSelected == 1) {
            viewModel.getBookmarkedPost()
        }
        super.onResume()
    }

    private fun initializeObservers() {
        viewModel.postList.observe(this, {
            setAdapter(PostAdapter(it, viewModel.postClickListner))
        })
        viewModel.errorMessage.observe(this, {
            Snackbar.make(root, it, Snackbar.LENGTH_LONG).show()
        })
        viewModel.postClickListner.observe(this, {
            val bundle = Bundle().apply {
                putString("title", it.title)
                putString("body", it.body)
                putInt("id", it.id)
            }
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        })
        TimeLineApp.isConnectToNetwork.observe(this, {
            if (!it)
                Snackbar.make(root, "Lost internet Connection", Snackbar.LENGTH_LONG).show()
        })
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.title.toString()) {
                getString(R.string.timeline) -> viewModel.getPost()
                getString(R.string.bookmark) -> viewModel.getBookmarkedPost()
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}