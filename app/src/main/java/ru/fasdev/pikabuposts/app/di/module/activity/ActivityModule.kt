package ru.fasdev.pikabuposts.app.di.module.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import ru.fasdev.pikabuposts.app.di.scope.ActivityScope

@Module
class ActivityModule (val fragmentActivity: FragmentActivity)
{
    @Provides
    @ActivityScope
    fun provideFragmentActivity(): FragmentActivity = fragmentActivity

    @Provides
    @ActivityScope
    fun provideAppCompatActivity(fragmentActivity: FragmentActivity): AppCompatActivity = fragmentActivity as AppCompatActivity
}