package ru.fasdev.pikabuposts.domain.post.interactor

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.model.Post

class PostInteractorImpl(val postRepo: PostRepo): PostInteractor
{
    override fun getPost(id: Long): Flow<Post>
    {
        return postRepo.getPost(id)
    }
}