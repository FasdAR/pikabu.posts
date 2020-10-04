package ru.fasdev.pikabuposts.data.source.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import ru.fasdev.pikabuposts.data.source.room.AppDataBase.Companion.IMAGE_TABLE_NAME

@Entity(
    tableName = IMAGE_TABLE_NAME,
    foreignKeys = [
        ForeignKey(entity = PostDB::class, parentColumns = ["id"], childColumns = ["idPost"], onDelete = CASCADE)
    ]
)
data class ImageDB(@PrimaryKey val id: Long?, val idPost: Long, val url: String)