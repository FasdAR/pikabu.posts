package ru.fasdev.pikabuposts.ui.adapter.epoxy.listFeed

import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import ru.fasdev.pikabuposts.domain.post.model.Post

class ListFeedController(val listenerFeed: ListFeedModel.Listener) : Typed2EpoxyController<List<Post>, List<Long>>()
{
    override fun buildModels(data: List<Post>?, listSaved: List<Long>)
    {
        data?.forEach { post->
            ListFeedModel_().apply {
                id(post.id)
                this.post = post
                stateIsSaved = listSaved.find { it == post.id} != null
                listener = listenerFeed
            }.addTo(this)
        }
    }
}