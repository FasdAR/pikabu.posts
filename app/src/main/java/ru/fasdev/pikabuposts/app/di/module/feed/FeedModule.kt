package ru.fasdev.pikabuposts.app.di.module.feed

import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import ru.fasdev.pikabuposts.data.post.dataStore.LocalPostDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.PostDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.source.RetrofitDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.source.RoomDataStore
import ru.fasdev.pikabuposts.data.post.repo.PostRepoImpl
import ru.fasdev.pikabuposts.data.source.retrofit.pikabu.api.PikabuApi
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostNetworkInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.interactor.PostInteractorImpl
import ru.fasdev.pikabuposts.domain.post.interactor.PostLocalInteractorImpl
import ru.fasdev.pikabuposts.domain.post.interactor.PostNetworkInteractorImpl

@Module
class FeedModule
{
    @Provides
    @Reusable
    fun providePostLocalInteractor(postRepo: PostRepo): PostLocalInteractor = PostLocalInteractorImpl(postRepo)

    @Provides
    @Reusable
    fun providePostNetworkInteractor(postRepo: PostRepo): PostNetworkInteractor = PostNetworkInteractorImpl(postRepo)
}