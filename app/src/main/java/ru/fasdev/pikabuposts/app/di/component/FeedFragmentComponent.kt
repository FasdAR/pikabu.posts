package ru.fasdev.pikabuposts.app.di.component

import dagger.Component
import ru.fasdev.pikabuposts.app.PikabuPostApp
import ru.fasdev.pikabuposts.app.di.scope.FragmentScope
import ru.fasdev.pikabuposts.ui.view.fragmentMainFeed.MainFeedFragment

@FragmentScope
@Component(dependencies = [ActivityComponent::class])
interface FeedFragmentComponent
{
    fun inject(fragment: MainFeedFragment)
}