package ru.fasdev.pikabuposts.app.di.module.repo

import dagger.Module
import dagger.Provides
import dagger.Reusable
import ru.fasdev.pikabuposts.app.di.scope.AppScope
import ru.fasdev.pikabuposts.data.post.dataStore.LocalPostDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.PostDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.source.RetrofitDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.source.RoomDataStore
import ru.fasdev.pikabuposts.data.post.repo.PostRepoImpl
import ru.fasdev.pikabuposts.data.source.retrofit.pikabu.api.PikabuApi
import ru.fasdev.pikabuposts.data.source.room.dao.PostDao
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo

@Module
class RepoModule
{
    @Provides
    @AppScope
    fun provideNetworkDataStore(pikabuApi: PikabuApi): PostDataStore = RetrofitDataStore(pikabuApi)

    @Provides
    @AppScope
    fun provideLocalDataStore(postDao: PostDao): LocalPostDataStore = RoomDataStore(postDao)

    @Provides
    @AppScope
    fun providePostRepo(networkDataStore: PostDataStore, localDataStore: LocalPostDataStore): PostRepo = PostRepoImpl(networkDataStore, localDataStore)
}