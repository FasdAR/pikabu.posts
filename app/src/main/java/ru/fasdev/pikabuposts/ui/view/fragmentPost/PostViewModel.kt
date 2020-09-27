package ru.fasdev.pikabuposts.ui.view.fragmentPost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.model.Post
import javax.inject.Inject

class PostViewModel: ViewModel() //@Inject constructor(val postInteractor: PostInteractor): ViewModel()
{
    var id: Long = 0L

    val post: MutableLiveData<Post> = MutableLiveData()
    val isRefreshed: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun setIdPost(id: Long)
    {
        this.id = id

        //loadPost()
    }

    /*
    fun loadPost()
    {
        viewModelScope.launch {
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
    }*/

    fun savedPost()
    {
        //TODO: ADD IMPL SAVED
    }
}