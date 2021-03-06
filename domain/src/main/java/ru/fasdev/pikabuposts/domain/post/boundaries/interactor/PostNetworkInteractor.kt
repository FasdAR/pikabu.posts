package ru.fasdev.pikabuposts.domain.post.boundaries.interactor

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.model.Post

interface PostNetworkInteractor
{
    fun getAllPosts(): Flow<List<Post>>
}