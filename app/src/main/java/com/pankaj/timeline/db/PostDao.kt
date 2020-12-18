package com.pankaj.timeline.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pankaj.timeline.data.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM Post")
    fun getAllPost(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<Post>)

    @Query("Update post set isBookmared=:bookmark and isUploaded=:upload where id =:postId")
    fun updatePost(postId: Int, bookmark: Boolean, upload: Boolean)

    @Query("Select * from Post where id in(:ids) and isBookmared = 1")
    fun postsBookmarked(ids: List<Int>): List<Post>

    @Query("Select * from Post where id =:postId and isBookmared = 1")
    fun isBookmarkedPost(postId: Int): Post?

    @Query("Select * from Post where isBookmared = 1")
    fun findBookmaredPost(): List<Post>

    @Query("Select * from Post where isBookmared = 1 and isUploaded = 0")
    fun findPendingPostUpload(): List<Post>

    @Query("Update post set isUploaded = 1 where id in(:ids)")
    fun updateUploadedPost(ids: List<Int>)


}