package ru.fasdev.pikabuposts.app.di.component

import android.content.Context
import dagger.Component
import retrofit2.Retrofit
import ru.fasdev.pikabuposts.app.PikabuPostApp
import ru.fasdev.pikabuposts.app.di.module.app.AppModule
import ru.fasdev.pikabuposts.app.di.module.app.RetrofitModule
import ru.fasdev.pikabuposts.app.di.scope.AppScope

@AppScope
@Component(modules = [AppModule::class, RetrofitModule::class])
interface AppComponent
{
    fun context(): Context
    fun retrofit(): Retrofit

    fun inject(app: PikabuPostApp)
}