package ru.fasdev.pikabuposts.ui.view.fragmentSubFeed

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostNetworkInteractor
import ru.fasdev.pikabuposts.domain.post.model.Post
import javax.inject.Inject

class SubFeedViewModel @Inject constructor(val postLocalInteractor: PostLocalInteractor, val postNetworkInteractor: PostNetworkInteractor) : ViewModel()
{
    private var mode: Int = SubFeedFragment.LOCAL_MODE

    val feed: MutableLiveData<List<Post>> = MutableLiveData()
    var isRefreshed: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    fun setModeFeed(mode: Int)
    {
        this.mode = mode

        loadNetworkData()
    }

    fun loadNetworkData() {
        viewModelScope.launch(Dispatchers.IO) {
            val flow: Flow<List<Post>> =
                if (mode == SubFeedFragment.LOCAL_MODE)
                    postLocalInteractor.getAllPosts()
                else
                    postNetworkInteractor.getAllPosts()

            flow
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
                    Log.e("EROROR", it.message.toString())
                }
                .collect {
                    feed.postValue(it)
                }
        }
    }

    fun savedPost(id: Long)
    {
        //TODO: ADD LOGIC BY SAVED POST
    }
}