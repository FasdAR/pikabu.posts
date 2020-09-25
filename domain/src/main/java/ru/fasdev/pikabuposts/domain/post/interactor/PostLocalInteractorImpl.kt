package ru.fasdev.pikabuposts.domain.post.interactor

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.entity.Post

class PostLocalInteractorImpl(val postRepo: PostRepo) : PostLocalInteractor
{
    override fun getAllCachedPosts(): Flow<List<Post>>
    {
        return postRepo.getAllCachedPosts()
    }

    override fun cachePost(post: Post)
    {
        postRepo.cachePost(post)
    }

    override fun isCachedPost(post: Post): Boolean
    {
        return postRepo.isCachedPost(post.id)
    }
}