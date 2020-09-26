package ru.fasdev.pikabuposts.ui.adapter.viewPager.fabric.source

import androidx.fragment.app.Fragment
import ru.fasdev.pikabuposts.ui.adapter.viewPager.fabric.VPFactory
import ru.fasdev.pikabuposts.ui.view.fragmentSubFeed.SubFeedFragment

class FeedFabric : VPFactory
{
    companion object {
        const val NETWORK_FEED_POS = 0;
        const val LOCAL_FEED_POS = 1;
    }

    override fun createFragment(position: Int): Fragment?
    {
        when (position)
        {
            NETWORK_FEED_POS -> return SubFeedFragment.newInstance(SubFeedFragment.NETWORK_MODE)
            LOCAL_FEED_POS -> return SubFeedFragment.newInstance(SubFeedFragment.LOCAL_MODE)
            else -> return null
        }
    }

    override fun getSize(): Int
    {
        return 2
    }
}