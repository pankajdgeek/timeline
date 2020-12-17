package com.pankaj.timeline.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pankaj.timeline.R
import com.pankaj.timeline.data.Comment
import com.pankaj.timeline.databinding.CommentLayoutBinding
import com.pankaj.timeline.ui.viewholder.CommentViewHolder

class CommentAdapter(val comment: List<Comment>) : RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = DataBindingUtil.inflate<CommentLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.comment_layout, parent, false
        )
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.onBind(comment[position])
    }

    override fun getItemCount(): Int {
        return comment.size
    }
}