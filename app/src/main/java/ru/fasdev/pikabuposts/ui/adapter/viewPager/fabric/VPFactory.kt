package ru.fasdev.pikabuposts.ui.adapter.viewPager.fabric

import androidx.fragment.app.Fragment

interface VPFactory
{
    fun createFragment(position: Int): Fragment?
    fun getSize(): Int
}