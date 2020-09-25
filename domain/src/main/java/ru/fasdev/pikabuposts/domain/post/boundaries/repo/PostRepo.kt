package ru.fasdev.pikabuposts.domain.post.boundaries.repo

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.model.Post


interface PostRepo
{
    fun getAllPosts(): Flow<List<Post>>
    fun getPost(id: Long ): Flow<Post>

    fun savePost(idPost: Long): Flow<Boolean>
    fun getAllSavedPosts(): Flow<List<Post>>
}