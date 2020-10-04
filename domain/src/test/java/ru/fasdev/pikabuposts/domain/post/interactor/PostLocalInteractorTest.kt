package ru.fasdev.pikabuposts.domain.post.interactor

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo

class PostLocalInteractorTest
{
    lateinit var postRepo: PostRepo
    lateinit var postLocalInteractor: PostLocalInteractor

    @Before
    fun setUp() {
        postRepo = mock(PostRepo::class.java)
        postLocalInteractor = PostLocalInteractorImpl(postRepo)
    }

    @Test
    fun testGetAllPost() {
        postLocalInteractor.getAllPosts()

        Mockito.verify(postRepo).getAllSavedPosts()
    }

    @Test
    fun testSavedPost()
    {
        val id = 1L

        postLocalInteractor.savePost(id)

        Mockito.verify(postRepo).savePost(id)
    }

    @Test
    fun testIsSaved()
    {
        val id = 1L

        postLocalInteractor.isSaved(id)

        Mockito.verify(postRepo).postIsSaved(id)
    }
}