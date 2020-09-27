package ru.fasdev.pikabuposts.app.di.component

import dagger.Component
import ru.fasdev.pikabuposts.app.di.module.app.RetrofitApiModule
import ru.fasdev.pikabuposts.app.di.module.feed.FeedModule
import ru.fasdev.pikabuposts.app.di.module.repo.RepoModule
import ru.fasdev.pikabuposts.app.di.scope.FragmentScope
import ru.fasdev.pikabuposts.ui.view.fragmentMainFeed.MainFeedFragment

@FragmentScope
@Component(dependencies = [ActivityComponent::class], modules = [FeedModule::class])
interface FeedFragmentComponent
{
    fun inject(fragment: MainFeedFragment)
}