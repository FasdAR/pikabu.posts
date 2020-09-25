package ru.fasdev.pikabuposts.domain.post.boundaries.interactor

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.entity.Post

interface PostLocalInteractor
{
    fun getAllCachedPosts(): Flow<List<Post>>
    fun cachePost(post: Post)
    fun isCachedPost(post: Post): Boolean
}