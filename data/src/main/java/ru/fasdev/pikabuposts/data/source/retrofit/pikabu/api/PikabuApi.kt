package ru.fasdev.pikabuposts.data.source.retrofit.pikabu.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.fasdev.pikabuposts.domain.post.model.Post

interface PikabuApi
{
    @GET("/page/interview/mobile-app/test-api/feed.php")
    suspend fun getFeed(): List<Post>

    @GET("/page/interview/mobile-app/test-api/story.php")
    suspend fun getStory(@Query("id") id: Long): Post
}