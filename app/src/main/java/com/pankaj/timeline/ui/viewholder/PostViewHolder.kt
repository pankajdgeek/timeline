package com.pankaj.timeline.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.pankaj.timeline.data.Post
import com.pankaj.timeline.databinding.PostLayoutBinding
import com.pankaj.timeline.ui.adapter.PostAdapter

class PostViewHolder(private val binding: PostLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(post: Post, postClickListner: PostAdapter.ClickListner) {
        binding.post = post
        binding.listner = postClickListner
    }
}