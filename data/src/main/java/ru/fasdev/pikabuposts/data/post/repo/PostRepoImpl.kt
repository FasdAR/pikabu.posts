package ru.fasdev.pikabuposts.data.post.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import ru.fasdev.pikabuposts.data.post.dataStore.LocalPostDataStore
import ru.fasdev.pikabuposts.data.post.dataStore.PostDataStore
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.domain.post.model.Post

class PostRepoImpl(val networkDataStore: PostDataStore, val localPostDataStore: LocalPostDataStore) : PostRepo
{
    override fun getAllPosts(): Flow<List<Post>>
    {
        return flow {
            emit( networkDataStore.getPosts().map { it -> it.isSaved = localPostDataStore.postIsSaved(it.id) } as List<Post>)
        }.flowOn(Dispatchers.IO)
    }

    override fun getPost(id: Long): Flow<Post>
    {
        if (localPostDataStore.postIsSaved(id))
            return flow { emit(localPostDataStore.getPost(id)) }.flowOn(Dispatchers.IO)
        else
            return flow { emit(networkDataStore.getPost(id)) }.flowOn(Dispatchers.IO)
    }

    override fun savePost(idPost: Long): Flow<Boolean>
    {
        return flow {
            localPostDataStore.savePost(networkDataStore.getPost(idPost))
            emit(localPostDataStore.postIsSaved(idPost))
        }.flowOn(Dispatchers.IO)
    }

    override fun getAllSavedPosts(): Flow<List<Post>>
    {
        return flow {
            emit(localPostDataStore.getPosts())
        }.flowOn(Dispatchers.IO)
    }
}