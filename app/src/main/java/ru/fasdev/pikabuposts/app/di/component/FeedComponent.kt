package ru.fasdev.pikabuposts.app.di.component

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Subcomponent
import ru.fasdev.pikabuposts.app.di.module.feed.FeedModule
import ru.fasdev.pikabuposts.app.di.scope.FeedScope
import ru.fasdev.pikabuposts.domain.post.boundaries.interactor.PostLocalInteractor
import ru.fasdev.pikabuposts.domain.post.boundaries.repo.PostRepo
import ru.fasdev.pikabuposts.ui.view.fragmentMainFeed.MainFeedFragment
import ru.fasdev.pikabuposts.ui.view.fragmentSubFeed.SubFeedFragment

@FeedScope
@Subcomponent(modules = [FeedModule::class])
interface FeedComponent
{
    @Subcomponent.Builder
    interface Builder {
        fun build(): FeedComponent
    }

    fun feedFragmentComponent(): FeedFragmentComponent.Builder
    fun postFragmentComponent(): PostFragmentComponent.Builder
}