package ru.fasdev.pikabuposts.data.source.room.dao

import androidx.room.*
import ru.fasdev.pikabuposts.data.source.room.AppDataBase.Companion.POST_TABLE_NAME
import ru.fasdev.pikabuposts.data.source.room.model.PostDB

@Dao
interface PostDao
{
    @Query("SELECT * FROM ${POST_TABLE_NAME}")
    fun getAll(): List<PostDB>

    @Query("SELECT * FROM ${POST_TABLE_NAME} WHERE id = :id")
    fun getById(id: Long): PostDB?

    @Insert
    fun insert(data: PostDB)

    @Query("DELETE FROM ${POST_TABLE_NAME} WHERE id = :id")
    fun removeById(id: Long)
}