package ru.fasdev.pikabuposts.data.post.dataStore.source

import ru.fasdev.pikabuposts.data.post.convertUtil.toPost
import ru.fasdev.pikabuposts.data.post.convertUtil.toPostDB
import ru.fasdev.pikabuposts.data.post.dataStore.LocalPostDataStore
import ru.fasdev.pikabuposts.data.source.room.dao.ImageDao
import ru.fasdev.pikabuposts.data.source.room.dao.PostDao
import ru.fasdev.pikabuposts.data.source.room.dao.PostImageDao
import ru.fasdev.pikabuposts.data.source.room.model.ImageDB
import ru.fasdev.pikabuposts.data.source.room.relation.PostImageRelation
import ru.fasdev.pikabuposts.domain.post.model.Post

class RoomDataStore(val postImageDao: PostImageDao, val postDao: PostDao) : LocalPostDataStore
{
    override fun savePost(post: Post?)
    {
        post?.let {
            val postDB = it.toPostDB()
            val arrayImageDB: ArrayList<ImageDB> = arrayListOf()

            it.images?.forEach {
              arrayImageDB.add(ImageDB(null, postDB.id, it))
            }

            postImageDao.insert(
                postDB,
                arrayImageDB
            )
        }
    }

    override fun postIsSaved(id: Long): Boolean
    {
        return postDao.getById(id) != null
    }

    override fun removePost(id: Long)
    {
        postDao.removeById(id)
    }

    override suspend fun getPosts(): List<Post>
    {
        val result = postImageDao.getAll()

        return result.map {
            convertToDomainModel(it)
        }
    }

    override suspend fun getPost(id: Long): Post?
    {
        return convertToDomainModel(postImageDao.get(id))
    }

    private fun convertToDomainModel(postImageRelation: PostImageRelation): Post {
        return postImageRelation.post.toPost(
            postImageRelation.images.map { it.url }
        )
    }
}