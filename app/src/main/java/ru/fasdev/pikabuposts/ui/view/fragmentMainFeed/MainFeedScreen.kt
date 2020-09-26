package ru.fasdev.pikabuposts.ui.view.fragmentMainFeed

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class MainFeedScreen : SupportAppScreen()
{
    override fun getFragment(): Fragment? {
        return MainFeedFragment.newInstance()
    }
}