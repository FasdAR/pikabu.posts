package ru.fasdev.pikabuposts.domain.post.interactor

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostNetworkInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.entity.Post

class PostNetworkInteractorImpl (val postRepo: PostRepo): PostNetworkInteractor
{
    override fun getAllPosts(): Flow<List<Post>>
    {
        //TODO: ADD SORTING
        return postRepo.getAllPosts()
    }
}