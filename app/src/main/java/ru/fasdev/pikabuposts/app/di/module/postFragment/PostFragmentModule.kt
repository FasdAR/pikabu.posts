package ru.fasdev.pikabuposts.app.di.module.postFragment

import dagger.Module
import dagger.Provides
import ru.fasdev.pikabuposts.app.di.scope.FragmentScope
import ru.fasdev.pikabuposts.app.di.scope.PostFragmentScope
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.interactor.PostInteractorImpl

@Module
class PostFragmentModule
{
    @Provides
    @PostFragmentScope
    fun providePostFragmentInteractor(postRepo: PostRepo): PostInteractor = PostInteractorImpl(postRepo)
}