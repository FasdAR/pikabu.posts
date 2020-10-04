package ru.fasdev.pikabuposts.data.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.fasdev.pikabuposts.data.source.room.AppDataBase.Companion.CURRENT_VERISON
import ru.fasdev.pikabuposts.data.source.room.dao.ImageDao
import ru.fasdev.pikabuposts.data.source.room.dao.PostDao
import ru.fasdev.pikabuposts.data.source.room.dao.PostImageDao
import ru.fasdev.pikabuposts.data.source.room.model.ImageDB
import ru.fasdev.pikabuposts.data.source.room.model.PostDB

@Database(entities = arrayOf(PostDB::class, ImageDB::class), version = CURRENT_VERISON)
abstract class AppDataBase: RoomDatabase()
{
    companion object {
        const val CURRENT_VERISON = 2
        const val DB_NAME = "PIKABU_POST_DB"

        const val POST_TABLE_NAME = "POST_TABLE"
        const val IMAGE_TABLE_NAME = "IMAGE_TABLE"
    }

    abstract fun postDao(): PostDao
    abstract fun imageDao(): ImageDao
    abstract fun postImageDao(): PostImageDao
}