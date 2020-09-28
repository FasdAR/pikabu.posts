package ru.fasdev.pikabuposts.app.di.module.app

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.fasdev.pikabuposts.app.di.scope.AppScope
import ru.fasdev.pikabuposts.app.network.NetworkErrorInteractorImpl
import ru.fasdev.pikabuposts.data.network.NetworkErrorInteractor

@Module
class NetworkModule
{
    @Provides
    @AppScope
    fun provideNetworkInteractor(context: Context): NetworkErrorInteractor = NetworkErrorInteractorImpl(context)
}