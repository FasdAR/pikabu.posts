package ru.fasdev.pikabuposts.data.source.room.relation

import androidx.room.Embedded
import androidx.room.Relation
import ru.fasdev.pikabuposts.data.source.room.model.ImageDB
import ru.fasdev.pikabuposts.data.source.room.model.PostDB

data class PostImageRelation
(
    @Embedded val post: PostDB,
    @Relation(parentColumn = "id", entityColumn = "idPost") val images: List<ImageDB>
)