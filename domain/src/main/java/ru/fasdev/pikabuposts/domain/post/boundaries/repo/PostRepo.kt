package ru.fasdev.pikabuposts.domain.post.boundaries.repo

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.entity.Post


interface PostRepo
{
    fun getAllPosts(): Flow<List<Post>>
    fun getAllCachedPosts(): Flow<List<Post>>
    fun cachePost(post: Post)
    fun isCachedPost(idPost: Int): Boolean
}