package ru.fasdev.pikabuposts.data.post.dataStore

import ru.fasdev.pikabuposts.domain.post.model.Post

interface LocalPostDataStore: PostDataStore
{
    fun savePost(post: Post?)
    fun postIsSaved(id: Long): Boolean
}