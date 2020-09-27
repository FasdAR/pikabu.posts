package ru.fasdev.pikabuposts.app.di.component

import android.content.Context
import androidx.room.Room
import dagger.Component
import retrofit2.Retrofit
import ru.fasdev.pikabuposts.app.PikabuPostApp
import ru.fasdev.pikabuposts.app.di.module.app.AppModule
import ru.fasdev.pikabuposts.app.di.module.app.RetrofitApiModule
import ru.fasdev.pikabuposts.app.di.module.app.RetrofitModule
import ru.fasdev.pikabuposts.app.di.module.app.RoomModule
import ru.fasdev.pikabuposts.app.di.module.repo.RepoModule
import ru.fasdev.pikabuposts.app.di.scope.AppScope

@AppScope
@Component(modules = [AppModule::class, RetrofitModule::class, RetrofitApiModule::class, RoomModule::class, RepoModule::class])
interface AppComponent
{
    fun context(): Context
    fun retrofit(): Retrofit

    fun inject(app: PikabuPostApp)
}