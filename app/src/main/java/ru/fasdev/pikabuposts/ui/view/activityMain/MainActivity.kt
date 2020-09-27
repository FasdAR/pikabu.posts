package ru.fasdev.pikabuposts.ui.view.activityMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.app.PikabuPostApp
import ru.fasdev.pikabuposts.app.di.module.activity.ActivityModule
import ru.fasdev.pikabuposts.app.di.module.activity.CiceroneModule
import ru.fasdev.pikabuposts.ui.view.fragmentMainFeed.MainFeedScreen
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var routerCicerone: Router

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var navigator: SupportAppNavigator

    val activitySubComponent by lazy {
        return@lazy PikabuPostApp.DI.appComponent
            .activityComponent()
            .activityModule(ActivityModule(this))
            .ciceroneModule(CiceroneModule(R.id.main_container))
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        activitySubComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getCurrentFragment() == null)
            routerCicerone.newRootScreen(MainFeedScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        navigationHolder.removeNavigator()
    }

    fun getCurrentFragment(): Fragment?
    {
        return supportFragmentManager.findFragmentById(R.id.main_container)
    }
}