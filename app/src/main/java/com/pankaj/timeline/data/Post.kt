package com.pankaj.timeline.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Post")
data class Post(
	@SerializedName("userId") val userId: Int,
	@PrimaryKey
	@SerializedName("id") val id: Int,
	@SerializedName("title") val title: String,
	@SerializedName("body") val body: String,
//	@Ignore
	@SerializedName("isBookmared") val isBookmarked: Boolean
)