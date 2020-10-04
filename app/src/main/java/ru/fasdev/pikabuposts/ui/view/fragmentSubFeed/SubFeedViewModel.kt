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
import org.greenrobot.eventbus.EventBus
import retrofit2.HttpException
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.app.lifecycle.SingleLiveEvent
import ru.fasdev.pikabuposts.app.lifecycle.ZipLiveData
import ru.fasdev.pikabuposts.data.network.NetworkErrorInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostNetworkInteractor
import ru.fasdev.pikabuposts.domain.post.model.Post
import ru.fasdev.pikabuposts.eventbus.event.UpdateSavedPost
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
    var mode: Int = SubFeedFragment.LOCAL_MODE
        set(value) {
            field = value

            if (feed.value.isNullOrEmpty())
                loadData(true)
            else
                loadData(false)
        }

    private val feed: MutableLiveData<List<Post>> = MutableLiveData()
    private val idSavedPosts: MutableLiveData<List<Long>> = MutableLiveData()

    val dataFeed = ZipLiveData.zipLiveData(feed, idSavedPosts)

    var isRefreshed: MutableLiveData<Boolean> = MutableLiveData()
    val errorFeed: SingleLiveEvent<String?> = SingleLiveEvent()
    val errorSnackbar: SingleLiveEvent<String> = SingleLiveEvent()

    fun loadData(isUser: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            loadPosts(isUser)
            loadIdSavedPosts()
        }
    }

    private suspend fun loadPosts(isUser: Boolean) {
        val flow: Flow<List<Post>> =
            if (mode == SubFeedFragment.LOCAL_MODE)
                postLocalInteractor.getAllPosts()
            else
                postNetworkInteractor.getAllPosts()

        flow
            .flowOn(Dispatchers.IO)
            .onStart {
                if (isUser) isRefreshed.postValue(true)
            }
            .onCompletion {
                if (isUser) isRefreshed.postValue(false)
            }
            .catch {
                if (feed.value.isNullOrEmpty())
                    errorFeed.postValue(networkErrorInteractor.getError(it))
                else
                    if (isUser) errorSnackbar.postValue(networkErrorInteractor.getError(it))
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

    fun updateSavedPost() {
        viewModelScope.launch(Dispatchers.IO) {
            if (mode == SubFeedFragment.LOCAL_MODE)
                loadData(false)
            else
                loadIdSavedPosts()
        }
    }

    fun savedPost(id: Long)
    {
        GlobalScope.launch(Dispatchers.IO) {
            postLocalInteractor.savePost(id)
                .flowOn(Dispatchers.IO)
                .catch {
                    errorSnackbar.postValue(networkErrorInteractor.getError(it))
                }
                .collect {
                    loadIdSavedPosts()
                    EventBus.getDefault().post(UpdateSavedPost(if (mode == SubFeedFragment.LOCAL_MODE) SubFeedFragment.NETWORK_MODE else SubFeedFragment.LOCAL_MODE));
                }

        }
    }
}