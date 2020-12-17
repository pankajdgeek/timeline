package com.pankaj.timeline.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.pankaj.timeline.R
import com.pankaj.timeline.data.Post
import com.pankaj.timeline.databinding.PostLayoutBinding
import com.pankaj.timeline.ui.viewholder.PostViewHolder

class PostAdapter(val posts: List<Post>, val clickedData: MutableLiveData<Post>) :
    RecyclerView.Adapter<PostViewHolder>() {
    interface ClickListner {
        fun onClick(post: Post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = DataBindingUtil.inflate<PostLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.post_layout, parent, false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.onBind(posts[position], object : ClickListner {
            override fun onClick(post: Post) {
                clickedData.postValue(post)
            }
        })
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}