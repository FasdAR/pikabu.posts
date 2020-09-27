package ru.fasdev.pikabuposts.app.di.module.app

import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import ru.fasdev.pikabuposts.app.di.scope.AppScope
import ru.fasdev.pikabuposts.data.source.retrofit.pikabu.api.PikabuApi

@Module
class RetrofitApiModule
{
    @Provides
    @AppScope
    fun providePikabuApi(retrofit: Retrofit): PikabuApi = retrofit.create(PikabuApi::class.java)
}