package ru.fasdev.pikabuposts.ui.view.fragmentSubFeed

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.app.lifecycle.ViewModelFactory
import ru.fasdev.pikabuposts.app.lifecycle.injectViewModel
import ru.fasdev.pikabuposts.databinding.SubFeedFragmentBinding
import ru.fasdev.pikabuposts.ui.view.activityMain.MainActivity
import ru.fasdev.pikabuposts.ui.view.fragmentMainFeed.MainFeedFragment
import javax.inject.Inject

class SubFeedFragment : Fragment()
{

    companion object
    {
        const val MODE_KEY = "mk"

        const val NETWORK_MODE = 0;
        const val LOCAL_MODE = 1;

        fun newInstance(mode: Int): SubFeedFragment {
            val arguments = bundleOf(MODE_KEY to mode)

            return SubFeedFragment().apply {
                this.arguments = arguments
            }
        }
    }

    private lateinit var viewModel: SubFeedViewModel
    private lateinit var binding: SubFeedFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val fragmentFeedComponent by lazy {
        return@lazy (parentFragment as MainFeedFragment).fragmentSubComponent
            .feedComponent()
            .build()
            .feedFragmentComponent()
            .build()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        fragmentFeedComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = SubFeedFragmentBinding.inflate(inflater)

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadNetworkData()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.setModeFeed(getMode())

        viewModel.feed.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                binding.listFeed.visibility = View.GONE
                binding.textError.visibility = View.VISIBLE

                binding.textError.setText(resources.getString(R.string.empty_list))
            }
            else
            {
                binding.textError.visibility = View.GONE
                binding.listFeed.visibility = View.VISIBLE
            }
        })

        viewModel.isRefreshed.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing = it
        })
    }

    fun getMode(): Int = arguments?.getInt(MODE_KEY) ?: LOCAL_MODE
}