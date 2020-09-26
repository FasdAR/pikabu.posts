package ru.fasdev.pikabuposts.app.di.module.activity

import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import ru.fasdev.pikabuposts.app.di.scope.ActivityScope
import ru.fasdev.pikabuposts.ui.cicerone.navigator.MainNavigator
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@Module
class CiceroneModule(val idContainer: Int)
{
    @Provides
    @ActivityScope
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @ActivityScope
    fun provideCiceroneNavigationHelper(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @ActivityScope
    fun provideCiceroneRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @ActivityScope
    //Provide Default MainNavigator
    fun provideNavigator(fragmentActivity: FragmentActivity): SupportAppNavigator = MainNavigator(fragmentActivity, idContainer)
}