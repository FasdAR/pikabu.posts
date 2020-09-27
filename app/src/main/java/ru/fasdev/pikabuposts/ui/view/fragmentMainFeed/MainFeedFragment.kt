package ru.fasdev.pikabuposts.ui.view.fragmentMainFeed

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.databinding.MainFeedFragmentBinding
import ru.fasdev.pikabuposts.ui.adapter.viewPager.VPFragments
import ru.fasdev.pikabuposts.ui.adapter.viewPager.fabric.source.FeedFabric
import ru.fasdev.pikabuposts.ui.cicerone.navigator.MainNavigator
import ru.fasdev.pikabuposts.ui.view.activityMain.MainActivity
import javax.inject.Inject

class MainFeedFragment : Fragment()
{
    companion object
    {
        fun newInstance() = MainFeedFragment()
    }

    private lateinit var viewModel: MainFeedViewModel
    private lateinit var binding: MainFeedFragmentBinding

    private lateinit var vpAdapter: VPFragments

    @Inject
    lateinit var appCompactActivity: AppCompatActivity

    private val titlesList by lazy {
        listOf(resources.getString(R.string.feed_posts), resources.getString(R.string.saved_posts))
    }

    val fragmentSubComponent by lazy {
        return@lazy (requireActivity() as MainActivity).activitySubComponent
            .fragmentComponent()
            .build()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        fragmentSubComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = MainFeedFragmentBinding.inflate(inflater)

        val toolbar = binding.root.findViewById<Toolbar>(R.id.toolbar)

        appCompactActivity.setSupportActionBar(toolbar)
        appCompactActivity.supportActionBar?.title = ""

        toolbar.setTitle(resources.getString(R.string.app_name))

        vpAdapter = VPFragments(childFragmentManager, viewLifecycleOwner.lifecycle, FeedFabric())
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = vpAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager, object : TabLayoutMediator.TabConfigurationStrategy
        {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int)
            {
                tab.text = titlesList.get(position)
            }
        }).attach()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainFeedViewModel::class.java)
    }
}