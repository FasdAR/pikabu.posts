package ru.fasdev.pikabuposts.ui.view.fragmentPost

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.fasdev.pikabuposts.app.lifecycle.ZipLiveData
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.model.Post
import javax.inject.Inject

class PostViewModel @Inject constructor(val postInteractor: PostInteractor, val localInteractor: PostLocalInteractor): ViewModel()
{
    var id: Long = 0L

    private val post: MutableLiveData<Post> = MutableLiveData()
    private val isSaved: MutableLiveData<Boolean> = MutableLiveData()
    val isRefreshed: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    val data = ZipLiveData.zipLiveData(post, isSaved)

    init {
        Log.d("MODEL", System.identityHashCode(localInteractor).toString())
    }

    fun setIdPost(id: Long)
    {
        this.id = id

        loadPost()
        loadIsSaved(id)
    }

    fun loadPost()
    {
        viewModelScope.launch(Dispatchers.IO) {
            postInteractor.getPost(id)
                .flowOn(Dispatchers.IO)
                .onStart {
                    isRefreshed.postValue(true)
                }
                .onCompletion {
                    isRefreshed.postValue(false)
                }
                .catch {
                    //TODO: CHANGE TO NORMAL ERROR
                    error.postValue(it.message.toString())
                }
                .collect {
                    post.postValue(it)
                }
        }
    }

    private fun loadIsSaved(id: Long)
    {
        viewModelScope.launch(Dispatchers.IO) {
            localInteractor.isSaved(id)
                .collect {
                    isSaved.postValue(it)
                }
        }
    }

    fun savedPost()
    {
        viewModelScope.launch(Dispatchers.IO) {
            localInteractor.savePost(id)
                .flowOn(Dispatchers.IO)
                .catch {  }
                .collect{
                    loadIsSaved(id)
                }
        }
    }
}