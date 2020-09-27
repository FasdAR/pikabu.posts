package ru.fasdev.pikabuposts.app.di.component

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Subcomponent
import ru.fasdev.pikabuposts.app.di.module.feedFragment.FeedFragmentModule
import ru.fasdev.pikabuposts.app.di.scope.FeedFragmentScope
import ru.fasdev.pikabuposts.app.di.scope.FragmentScope
import ru.fasdev.pikabuposts.ui.view.fragmentSubFeed.SubFeedFragment

@FeedFragmentScope
@Subcomponent(modules = [FeedFragmentModule::class])
interface FeedFragmentComponent
{
    @Subcomponent.Builder
    interface Builder {
        fun build(): FeedFragmentComponent
    }

    fun inject(subFeedFragment: SubFeedFragment)
}