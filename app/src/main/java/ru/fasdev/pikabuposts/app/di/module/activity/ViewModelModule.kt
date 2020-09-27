package ru.fasdev.pikabuposts.app.di.module.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.fasdev.pikabuposts.app.di.key.ViewModelKey
import ru.fasdev.pikabuposts.app.lifecycle.ViewModelFactory
import ru.fasdev.pikabuposts.ui.view.fragmentPost.PostViewModel
import ru.fasdev.pikabuposts.ui.view.fragmentSubFeed.SubFeedViewModel

@Module
abstract class ViewModelModule
{
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SubFeedViewModel::class)
    abstract fun subViewModel(subVm: SubFeedViewModel): ViewModel
}