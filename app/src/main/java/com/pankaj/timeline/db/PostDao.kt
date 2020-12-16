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
     fun insertAll( users: List<Post>)
}