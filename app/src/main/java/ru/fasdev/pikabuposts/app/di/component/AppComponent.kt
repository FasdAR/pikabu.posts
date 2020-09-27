package ru.fasdev.pikabuposts.app.di.component

import android.content.Context
import androidx.room.Room
import dagger.Component
import dagger.Subcomponent
import retrofit2.Retrofit
import ru.fasdev.pikabuposts.app.PikabuPostApp
import ru.fasdev.pikabuposts.app.di.module.app.AppModule
import ru.fasdev.pikabuposts.app.di.module.app.RetrofitApiModule
import ru.fasdev.pikabuposts.app.di.module.app.RetrofitModule
import ru.fasdev.pikabuposts.app.di.module.app.RoomModule
import ru.fasdev.pikabuposts.app.di.scope.AppScope
import ru.fasdev.pikabuposts.data.source.retrofit.pikabu.api.PikabuApi
import ru.fasdev.pikabuposts.data.source.room.dao.PostDao

@AppScope
@Component(modules = [AppModule::class, RetrofitModule::class, RetrofitApiModule::class, RoomModule::class])
interface AppComponent
{
    fun context(): Context
    fun pikabuApi(): PikabuApi
    fun postDap(): PostDao

    fun inject(app: PikabuPostApp)

    fun activityComponent(): ActivityComponent.Builder
}