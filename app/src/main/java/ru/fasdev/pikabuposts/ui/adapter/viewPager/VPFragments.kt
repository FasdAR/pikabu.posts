package ru.fasdev.pikabuposts.ui.adapter.viewPager

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fasdev.pikabuposts.ui.adapter.viewPager.fabric.VPFactory

class VPFragments (val fm: FragmentManager, val lifecycle: Lifecycle, val fabricFragment: VPFactory): FragmentStateAdapter(fm, lifecycle)
{
    override fun getItemCount(): Int
    {
        return fabricFragment.getSize()
    }

    override fun createFragment(position: Int): Fragment
    {
        val createFragment = fabricFragment.createFragment(position)

        createFragment?.let {
            return it
        } ?: kotlin.run {
            return Fragment()
        }
    }
}