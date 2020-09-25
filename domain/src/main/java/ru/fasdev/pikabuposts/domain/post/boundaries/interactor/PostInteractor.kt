package ru.fasdev.pikabuposts.domain.post.boundaries.interactor

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.model.Post

interface PostInteractor
{
    fun getPost(id: Long): Flow<Post>
}