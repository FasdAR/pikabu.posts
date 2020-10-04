package ru.fasdev.pikabuposts.domain.post.interactor

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.interactor.PostInteractorImpl

class PostInteractorTest
{
    lateinit var postRepo: PostRepo
    lateinit var postInteractor: PostInteractor

    @Before
    fun setUp() {
        postRepo = mock(PostRepo::class.java)
        postInteractor = PostInteractorImpl(postRepo)
    }

    @Test
    fun testGetPost() {
        val testId = 10L

        postInteractor.getPost(testId)

        verify(postRepo).getPost(testId)
    }
}