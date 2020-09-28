package ru.fasdev.pikabuposts.app.di.module.postFragment

import dagger.Module
import dagger.Provides
import ru.fasdev.pikabuposts.app.di.scope.FeedScope
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.interactor.PostInteractorImpl

@Module
class PostFragmentModule
{
    @Provides
    @FeedScope
    fun providePostFragmentInteractor(postRepo: PostRepo): PostInteractor = PostInteractorImpl(postRepo)
}