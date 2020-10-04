package ru.fasdev.pikabuposts.data.source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.fasdev.pikabuposts.data.source.room.AppDataBase.Companion.IMAGE_TABLE_NAME
import ru.fasdev.pikabuposts.data.source.room.model.ImageDB

@Dao
interface ImageDao
{
    @Query("SELECT * FROM ${IMAGE_TABLE_NAME}")
    fun getAll(): List<ImageDB>

    @Insert
    fun insert(data: List<ImageDB>)
}