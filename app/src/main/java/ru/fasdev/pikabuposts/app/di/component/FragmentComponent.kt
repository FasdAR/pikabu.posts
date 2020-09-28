package ru.fasdev.pikabuposts.app.di.component

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Subcomponent
import ru.fasdev.pikabuposts.app.PikabuPostApp
import ru.fasdev.pikabuposts.app.di.module.app.AppModule
import ru.fasdev.pikabuposts.app.di.module.app.RetrofitApiModule
import ru.fasdev.pikabuposts.app.di.module.app.RetrofitModule
import ru.fasdev.pikabuposts.app.di.module.app.RoomModule
import ru.fasdev.pikabuposts.app.di.scope.AppScope
import ru.fasdev.pikabuposts.app.di.scope.FragmentScope
import ru.fasdev.pikabuposts.data.source.retrofit.pikabu.api.PikabuApi
import ru.fasdev.pikabuposts.data.source.room.dao.PostDao
import ru.fasdev.pikabuposts.ui.view.fragmentMainFeed.MainFeedFragment

@FragmentScope
@Subcomponent()
interface FragmentComponent
{
    @Subcomponent.Builder
    interface Builder {
        fun build(): FragmentComponent
    }

    fun inject(mainFeedFragment: MainFeedFragment)
}