package ru.fasdev.pikabuposts.app.di.component

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Subcomponent
import retrofit2.Retrofit
import ru.fasdev.pikabuposts.app.di.module.activity.ActivityModule
import ru.fasdev.pikabuposts.app.di.module.activity.CiceroneModule
import ru.fasdev.pikabuposts.app.di.module.activity.ViewModelModule
import ru.fasdev.pikabuposts.app.di.scope.ActivityScope
import ru.fasdev.pikabuposts.data.source.retrofit.pikabu.api.PikabuApi
import ru.fasdev.pikabuposts.data.source.room.dao.PostDao
import ru.fasdev.pikabuposts.ui.view.activityMain.MainActivity

@ActivityScope
@Subcomponent(modules = [ActivityModule::class, CiceroneModule::class, ViewModelModule::class])
interface ActivityComponent
{
    fun inject(mainActivity: MainActivity)

    fun feedComponent(): FeedComponent.Builder
    fun fragmentComponent(): FragmentComponent.Builder

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(module: ActivityModule):Builder
        fun ciceroneModule(module: CiceroneModule): Builder
        fun build(): ActivityComponent
    }
}