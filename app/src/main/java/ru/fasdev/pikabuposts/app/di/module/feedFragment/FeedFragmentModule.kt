package ru.fasdev.pikabuposts.app.di.module.feedFragment

import dagger.Module
import dagger.Provides
import ru.fasdev.pikabuposts.app.di.scope.FeedFragmentScope
import ru.fasdev.pikabuposts.app.di.scope.FeedScope
import ru.fasdev.pikabuposts.app.di.scope.FragmentScope
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostNetworkInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.interactor.PostNetworkInteractorImpl

@Module
class FeedFragmentModule
{
    @Provides
    @FeedScope
    fun provideNetworkInteractor(postRepo: PostRepo): PostNetworkInteractor = PostNetworkInteractorImpl(postRepo)
}