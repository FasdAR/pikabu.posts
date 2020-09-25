package ru.fasdev.pikabuposts.data.post.dataStore.source

import ru.fasdev.pikabuposts.data.post.dataStore.PostDataStore
import ru.fasdev.pikabuposts.data.source.retrofit.pikabu.api.PikabuApi
import ru.fasdev.pikabuposts.domain.post.model.Post

class RetrofitDataStore(val pikabuApi: PikabuApi) : PostDataStore
{
    override suspend fun getPosts(): List<Post>
    {
        return pikabuApi.getFeed()
    }

    override suspend fun getPost(id: Long): Post 
    {
        return pikabuApi.getStory(id)
    }
}