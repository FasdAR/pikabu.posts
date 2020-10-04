package ru.fasdev.pikabuposts.data.source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.fasdev.pikabuposts.data.source.room.AppDataBase.Companion.POST_TABLE_NAME
import ru.fasdev.pikabuposts.data.source.room.model.ImageDB
import ru.fasdev.pikabuposts.data.source.room.model.PostDB
import ru.fasdev.pikabuposts.data.source.room.relation.PostImageRelation

@Dao
public abstract class PostImageDao()
{
    @Insert
    abstract fun insertPost(data: PostDB)

    @Insert
    abstract fun insertImages(data: List<ImageDB>)

    @Transaction
    open fun insert(postDB: PostDB, images: List<ImageDB>)
    {
        insertPost(postDB)
        insertImages(images)
    }

    @Transaction
    @Query("SELECT * from ${POST_TABLE_NAME} WHERE id = :id")
    abstract fun get(id: Long): PostImageRelation

    @Transaction
    @Query("SELECT * from ${POST_TABLE_NAME}")
    abstract fun getAll(): List<PostImageRelation>
}