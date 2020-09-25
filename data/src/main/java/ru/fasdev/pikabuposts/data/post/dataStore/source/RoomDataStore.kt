package ru.fasdev.pikabuposts.data.post.dataStore.source

import ru.fasdev.pikabuposts.data.post.convertUtil.toPost
import ru.fasdev.pikabuposts.data.post.convertUtil.toPostDB
import ru.fasdev.pikabuposts.data.post.dataStore.LocalPostDataStore
import ru.fasdev.pikabuposts.data.source.room.dao.PostDao
import ru.fasdev.pikabuposts.domain.post.model.Post

class RoomDataStore(val postDao: PostDao) : LocalPostDataStore
{
    override fun savePost(post: Post)
    {
        return postDao.insertPost(post.toPostDB())
    }

    override fun postIsSaved(id: Long): Boolean
    {
        //TODO: CHANGE TO NORMAL CHECK
        return postDao.getById(id) != null
    }

    override suspend fun getPosts(): List<Post>
    {
        return postDao.getAll().map { it -> it.toPost() }
    }

    override suspend fun getPost(id: Long): Post?
    {
        return postDao.getById(id)?.toPost()
    }
}