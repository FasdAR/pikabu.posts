package ru.fasdev.pikabuposts.ui.adapter.epoxy.listFeed

import com.airbnb.epoxy.TypedEpoxyController
import ru.fasdev.pikabuposts.domain.post.model.Post

class ListFeedController(val listenerFeed: ListFeedModel.Listener) : TypedEpoxyController<List<Post>>()
{
    override fun buildModels(data: List<Post>?)
    {
        data?.forEach {
            val model = ListFeedModel_().apply {
                id(it.id)
                post = it
                listener = listenerFeed
            }

            add(model)
        }
    }
}