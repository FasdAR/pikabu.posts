package ru.fasdev.pikabuposts.data.post.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.http.POST
import ru.fasdev.pikabuposts.data.post.dataStore.LocalPostDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.PostDataStore
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.model.Post

class PostRepoImpl(val networkDataStore: PostDataStore, val localPostDataStore: LocalPostDataStore) : PostRepo
{
    override fun getAllPosts(): Flow<List<Post>>
    {
        return flow {
            emit(networkDataStore.getPosts())
        }.flowOn(Dispatchers.IO)
    }

    override fun getPost(id: Long): Flow<Post?>
    {
        return flow {
            val post: Post? = if (localPostDataStore.postIsSaved(id)) localPostDataStore.getPost(id) else networkDataStore.getPost(id)
            emit(post)
        }
    }

    override fun savePost(idPost: Long): Flow<Boolean>
    {
        return flow {
            if (localPostDataStore.postIsSaved(idPost))
            {
                localPostDataStore.removePost(idPost)
            }
            else
            {
                localPostDataStore.savePost(networkDataStore.getPost(idPost))
            }

            emit(localPostDataStore.postIsSaved(idPost))
        }.flowOn(Dispatchers.IO)
    }

    override fun getAllSavedPosts(): Flow<List<Post>>
    {
        return flow {
            emit(localPostDataStore.getPosts())
        }.flowOn(Dispatchers.IO)
    }

    override fun postIsSaved(id: Long): Flow<Boolean>
    {
        return flow { emit(localPostDataStore.postIsSaved(id)) }.flowOn(Dispatchers.IO)
    }
}