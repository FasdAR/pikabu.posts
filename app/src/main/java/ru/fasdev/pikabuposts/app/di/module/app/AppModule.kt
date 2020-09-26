package ru.fasdev.pikabuposts.app.di.module.app

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.fasdev.pikabuposts.app.di.scope.AppScope

@Module
class AppModule (val context: Context)
{
    @Provides
    @AppScope
    fun provideContext(): Context = context
}