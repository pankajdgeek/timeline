package com.pankaj.timeline.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pankaj.pankaj.util.printInfoLog
import com.pankaj.timeline.R
import com.pankaj.timeline.databinding.ActivityMainBinding
import com.pankaj.timeline.ui.adapter.PostAdapter
import com.pankaj.timeline.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.viewModel = viewModel
        initializeObservers()
        viewModel.getPost()

    }

    private fun setAdapter(adapter: PostAdapter) {
        rv_post.layoutManager = LinearLayoutManager(this)
        rv_post.adapter = adapter
    }

    private fun initializeObservers() {
        viewModel.postList.observe(this, {
            printInfoLog(this, " get the resutl $it")
            setAdapter(PostAdapter(it))
        })
        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()

        })
        viewModel.isLoading

    }
}