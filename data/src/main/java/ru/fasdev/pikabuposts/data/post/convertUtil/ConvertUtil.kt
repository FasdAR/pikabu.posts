package ru.fasdev.pikabuposts.data.post.convertUtil

import ru.fasdev.pikabuposts.data.source.room.model.PostDB
import ru.fasdev.pikabuposts.domain.post.model.Post

fun Post.toPostDB(): PostDB = PostDB(id, title, body)
fun PostDB.toPost(images: List<String>?): Post = Post(id, title, body, images)