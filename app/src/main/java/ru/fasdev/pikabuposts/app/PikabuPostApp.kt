package ru.fasdev.pikabuposts.app

import android.app.Application
import ru.fasdev.pikabuposts.app.di.component.AppComponent
import ru.fasdev.pikabuposts.app.di.component.DaggerAppComponent
import ru.fasdev.pikabuposts.app.di.module.app.AppModule

class PikabuPostApp : Application()
{
    object DI
    {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        DI.appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(applicationContext))
            .build()
    }
}