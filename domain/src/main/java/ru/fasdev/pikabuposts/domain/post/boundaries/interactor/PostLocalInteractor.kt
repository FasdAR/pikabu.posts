package ru.fasdev.pikabuposts.domain.post.boundaries.interactor

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.model.Post

interface PostLocalInteractor
{
    fun getAllPosts(): Flow<List<Post>>
    fun savePost(idPost: Long)
}