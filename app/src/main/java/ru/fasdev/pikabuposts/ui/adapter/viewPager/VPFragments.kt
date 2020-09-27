package ru.fasdev.pikabuposts.ui.adapter.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fasdev.pikabuposts.ui.adapter.viewPager.fabric.VPFactory

class VPFragments (val fm: FragmentManager, val lifecycle: Lifecycle, val fabricFragment: VPFactory): FragmentStateAdapter(fm, lifecycle)
{
    private val listFragments: ArrayList<Fragment?> = arrayListOf()

    override fun getItemCount(): Int
    {
        return fabricFragment.getSize()
    }

    override fun createFragment(position: Int): Fragment
    {
        val createFragment = fabricFragment.createFragment(position)

        listFragments.add(createFragment)

        createFragment?.let {
            return it
        } ?: kotlin.run {
            return Fragment()
        }
    }
}