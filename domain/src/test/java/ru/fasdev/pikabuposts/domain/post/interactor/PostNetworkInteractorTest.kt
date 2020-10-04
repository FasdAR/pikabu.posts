package ru.fasdev.pikabuposts.domain.post.interactor

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostNetworkInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo

class PostNetworkInteractorTest
{
    lateinit var postRepo: PostRepo
    lateinit var postNetworkInteractor: PostNetworkInteractor

    @Before
    fun setUp() {
        postRepo = mock(PostRepo::class.java)
        postNetworkInteractor = PostNetworkInteractorImpl(postRepo)
    }

    @Test
    fun testGetAllPosts()
    {
        postNetworkInteractor.getAllPosts()

        Mockito.verify(postRepo).getAllPosts()
    }
}