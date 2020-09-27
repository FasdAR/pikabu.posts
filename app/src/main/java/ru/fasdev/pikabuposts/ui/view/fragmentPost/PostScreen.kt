package ru.fasdev.pikabuposts.ui.view.fragmentPost

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class PostScreen (val id: Long): SupportAppScreen()
{
    override fun getFragment(): Fragment? {
        return PostFragment.newInstance(id)
    }
}