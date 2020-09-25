package ru.fasdev.pikabuposts.data.source.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.fasdev.pikabuposts.data.source.room.AppDataBase.Companion.POST_TABLE_NAME
import ru.fasdev.pikabuposts.data.source.room.model.PostDB
import ru.fasdev.pikabuposts.domain.post.model.Post

@Dao
interface PostDao
{
    @Query("SELECT * FROM ${POST_TABLE_NAME}")
    fun getAll(): List<PostDB>

    @Query("SELECT * FROM ${POST_TABLE_NAME} WHERE id = :id")
    fun getById(id: Long): PostDB?

    @Insert
    fun insertPost(post: PostDB)

    @Query("DELETE FROM ${POST_TABLE_NAME} WHERE id = :id")
    fun removeById(id: Long)
}