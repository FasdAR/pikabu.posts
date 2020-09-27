package ru.fasdev.pikabuposts.domain.post.interactor

import kotlinx.coroutines.flow.Flow
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.model.Post

class PostLocalInteractorImpl(val postRepo: PostRepo) : PostLocalInteractor
{
    override fun getAllPosts(): Flow<List<Post>>
    {
        return postRepo.getAllSavedPosts()
    }

    override fun savePost(idPost: Long): Flow<Boolean>
    {
        return postRepo.savePost(idPost)
    }

    override fun isSaved(idPost: Long): Flow<Boolean>
    {
        return postRepo.postIsSaved(idPost)
    }
}