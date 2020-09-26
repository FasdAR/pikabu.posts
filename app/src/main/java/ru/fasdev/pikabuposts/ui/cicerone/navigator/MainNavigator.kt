
package ru.fasdev.pikabuposts.ui.cicerone.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import ru.fasdev.pikabuposts.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class MainNavigator (activity: FragmentActivity, containerId: Int): SupportAppNavigator(activity, containerId)
{
    override fun setupFragmentTransaction(command: Command, currentFragment: Fragment?, nextFragment: Fragment?, fragmentTransaction: FragmentTransaction)
    {
        fragmentTransaction.setCustomAnimations(R.anim.fade_out, R.anim.fade_in, R.anim.fade_out, R.anim.fade_in)
    }
}
