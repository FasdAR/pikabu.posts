package ru.fasdev.pikabuposts.domain.post.boundaries.interactor

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.entity.Post

interface PostNetworkInteractor
{
    fun getAllPosts(): Flow<List<Post>>
}