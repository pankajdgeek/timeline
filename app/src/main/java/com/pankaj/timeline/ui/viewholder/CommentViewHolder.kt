package com.pankaj.timeline.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.pankaj.timeline.data.Comment
import com.pankaj.timeline.databinding.CommentLayoutBinding

class CommentViewHolder(private val binding: CommentLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(comment: Comment) {
        binding.comment = comment
    }
}