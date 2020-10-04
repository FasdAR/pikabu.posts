package ru.fasdev.pikabuposts.data.source.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.fasdev.pikabuposts.data.source.room.AppDataBase.Companion.POST_TABLE_NAME

@Entity(tableName = POST_TABLE_NAME)
data class PostDB (@PrimaryKey val id: Long, val title: String?, val body: String?)