package ru.fasdev.pikabuposts.domain.post.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostNetworkInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.model.Post

class PostNetworkInteractorImpl (val postRepo: PostRepo): PostNetworkInteractor
{
    override fun getAllPosts(): Flow<List<Post>>
    {
        return postRepo.getAllPosts().flowOn(Dispatchers.IO)
    }
}