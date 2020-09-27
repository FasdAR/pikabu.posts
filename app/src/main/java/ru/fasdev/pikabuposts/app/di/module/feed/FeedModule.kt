package ru.fasdev.pikabuposts.app.di.module.feed

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.fasdev.pikabuposts.app.di.scope.FeedScope
import ru.fasdev.pikabuposts.data.post.dataStore.LocalPostDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.PostDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.source.RetrofitDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.source.RoomDataStore
import ru.fasdev.pikabuposts.data.post.repo.PostRepoImpl
import ru.fasdev.pikabuposts.data.source.retrofit.pikabu.api.PikabuApi
import ru.fasdev.pikabuposts.data.source.room.dao.PostDao
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.interactor.PostLocalInteractorImpl

@Module
class FeedModule
{
    @Provides
    @FeedScope
    fun provideNetworkDataStore(pikabuApi: PikabuApi): PostDataStore = RetrofitDataStore(pikabuApi)

    @Provides
    @FeedScope
    fun provideLocalDataStore(postDao: PostDao): LocalPostDataStore = RoomDataStore(postDao)

    @Provides
    @FeedScope
    fun providePostRepository(networkDataStore: PostDataStore, localPostDataStore: LocalPostDataStore): PostRepo = PostRepoImpl(networkDataStore, localPostDataStore)

    @Provides
    @FeedScope
    fun providePostLocalInteractor(postRepo: PostRepo): PostLocalInteractor = PostLocalInteractorImpl(postRepo)
}