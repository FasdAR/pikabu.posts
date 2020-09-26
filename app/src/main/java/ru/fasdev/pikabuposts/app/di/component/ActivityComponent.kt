package ru.fasdev.pikabuposts.app.di.component

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import retrofit2.Retrofit
import ru.fasdev.pikabuposts.app.di.module.activity.ActivityModule
import ru.fasdev.pikabuposts.app.di.module.activity.CiceroneModule
import ru.fasdev.pikabuposts.app.di.scope.ActivityScope
import ru.fasdev.pikabuposts.ui.view.activityMain.MainActivity

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class, CiceroneModule::class])
interface ActivityComponent
{
    fun context(): Context
    fun appCompatActivity(): AppCompatActivity
    fun retrofit(): Retrofit

    fun inject(mainActivity: MainActivity)
}