package ru.fasdev.pikabuposts.app.di.module.app

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.fasdev.pikabuposts.app.di.scope.AppScope
import ru.fasdev.pikabuposts.data.source.room.AppDataBase
import ru.fasdev.pikabuposts.data.source.room.dao.PostDao

@Module
class RoomModule
{
    @Provides
    @AppScope
    fun provideAppDatabase(context: Context): AppDataBase = Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.DB_NAME).fallbackToDestructiveMigration().build()

    @Provides
    @AppScope
    fun providePostDao(appDataBase: AppDataBase): PostDao = appDataBase.postDao()
}