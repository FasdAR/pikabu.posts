package ru.fasdev.pikabuposts.app.di.module.app

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.fasdev.pikabuposts.BuildConfig
import ru.fasdev.pikabuposts.app.di.scope.AppScope

@Module
class RetrofitModule
{
    @Provides
    @AppScope
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @AppScope
    fun provideHttpLogginInteractor(): HttpLoggingInterceptor {
        val httpInteractor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            httpInteractor.level = HttpLoggingInterceptor.Level.BODY
        else
            httpInteractor.level = HttpLoggingInterceptor.Level.NONE

        return httpInteractor
    }

    @Provides
    @AppScope
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    @Provides
    @AppScope
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .baseUrl("https://pikabu.ru")
        .build()
}