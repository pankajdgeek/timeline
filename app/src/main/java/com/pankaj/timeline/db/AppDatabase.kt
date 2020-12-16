package com.pankaj.timeline.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pankaj.timeline.data.Post

@Database(entities = arrayOf(Post::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
