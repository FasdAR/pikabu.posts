package ru.fasdev.pikabuposts.ui.view.fragmentSubFeed

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.app.lifecycle.SingleLiveEvent
import ru.fasdev.pikabuposts.app.lifecycle.ZipLiveData
import ru.fasdev.pikabuposts.data.network.NetworkErrorInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostNetworkInteractor
import ru.fasdev.pikabuposts.domain.post.model.Post
import java.lang.Exception
import java.lang.RuntimeException
import java.net.UnknownHostException
import javax.inject.Inject

class SubFeedViewModel
    @Inject constructor(
        val postLocalInteractor: PostLocalInteractor,
        val postNetworkInteractor: PostNetworkInteractor,
        val networkErrorInteractor: NetworkErrorInteractor,
        val context: Context
    ) : ViewModel()
{
    private var mode: Int = SubFeedFragment.LOCAL_MODE

    private val feed: MutableLiveData<List<Post>> = MutableLiveData()
    private val idSavedPosts: MutableLiveData<List<Long>> = MutableLiveData()

    val dataFeed = ZipLiveData.zipLiveData(feed, idSavedPosts)

    var isRefreshed: MutableLiveData<Boolean> = MutableLiveData()
    val errorFeed: SingleLiveEvent<String?> = SingleLiveEvent()
    val errorSnackbar: SingleLiveEvent<String> = SingleLiveEvent()

    init {
        Log.d("MODEL", System.identityHashCode(postLocalInteractor).toString())
    }

    fun setModeFeed(mode: Int)
    {
        this.mode = mode

        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            loadPosts()
            loadIdSavedPosts()
        }
    }

    private suspend fun loadPosts() {
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
                if (feed.value.isNullOrEmpty())
                    errorFeed.postValue(networkErrorInteractor.getError(it))
                else
                    errorSnackbar.postValue(networkErrorInteractor.getError(it))
            }
            .collect {
                if (it.isNullOrEmpty())
                {
                    errorFeed.postValue(context.resources.getString(R.string.empty_list))
                }
                else
                {
                    errorFeed.postValue(null)
                    feed.postValue(it)
                }
            }
    }

    private suspend fun loadIdSavedPosts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                flow {
                    emit(
                        postLocalInteractor.getAllPosts()
                            .flatMapMerge { it.asFlow() }
                            .map { it.id }
                            .toList()
                    )
                }
                    .flowOn(Dispatchers.IO)
                    .collect {
                        idSavedPosts.postValue(it)
                    }
            }
        }
    }

    fun savedPost(id: Long)
    {
        GlobalScope.launch(Dispatchers.IO) {
            postLocalInteractor.savePost(id)
                .flowOn(Dispatchers.IO)
                .onStart {

                }
                .onCompletion {

                }
                .catch {
                    errorSnackbar.postValue(networkErrorInteractor.getError(it))
                }
                .collect {
                    loadIdSavedPosts()
                }

        }
    }
}