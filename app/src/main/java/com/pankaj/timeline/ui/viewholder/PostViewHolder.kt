package com.pankaj.timeline.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.pankaj.timeline.data.Post
import com.pankaj.timeline.databinding.PostLayoutBinding

class PostViewHolder(private val binding: PostLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(post: Post) {
        binding.post = post
    }
}