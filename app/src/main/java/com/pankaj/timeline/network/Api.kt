package com.pankaj.timeline.network

import com.pankaj.timeline.data.Comment
import com.pankaj.timeline.data.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET(" /posts/{post_id}/comments")
    suspend fun getPostComments(@Query("post_id") postId: Int): List<Comment>
}