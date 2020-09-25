package ru.fasdev.pikabuposts.data.post.dataStore

import ru.fasdev.pikabuposts.domain.post.model.Post

interface PostDataStore
{
    suspend fun getPosts(): List<Post>
    suspend fun getPost(id: Long): Post?
}