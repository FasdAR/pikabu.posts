package ru.fasdev.pikabuposts.data.source.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.fasdev.pikabuposts.data.source.room.AppDataBase.Companion.POST_TABLE_NAME
import ru.fasdev.pikabuposts.data.source.room.typeConverter.ImageListConverter

@Entity(tableName = POST_TABLE_NAME)
@TypeConverters(ImageListConverter::class)
data class PostDB (@PrimaryKey val id: Long, val title: String?, val body: String?, val images: List<String>?)