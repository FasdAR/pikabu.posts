package ru.fasdev.pikabuposts.ui.view.fragmentSubFeed

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.app.lifecycle.ViewModelFactory
import ru.fasdev.pikabuposts.app.lifecycle.injectViewModel
import ru.fasdev.pikabuposts.databinding.SubFeedFragmentBinding
import ru.fasdev.pikabuposts.ui.adapter.epoxy.listFeed.ListFeedController
import ru.fasdev.pikabuposts.ui.adapter.epoxy.listFeed.ListFeedModel
import ru.fasdev.pikabuposts.ui.view.fragmentMainFeed.MainFeedFragment
import ru.fasdev.pikabuposts.ui.view.fragmentPost.PostScreen
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SubFeedFragment : Fragment(), ListFeedModel.Listener
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

    @Inject
    lateinit var router: Router

    val fragmentFeedComponent by lazy {
        return@lazy (parentFragment as MainFeedFragment).fragmentSubComponent
            .feedComponent()
            .build()
    }

    val listFeedController: ListFeedController = ListFeedController(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        fragmentFeedComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = SubFeedFragmentBinding.inflate(inflater)

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadData()
        }

        binding.listFeed.layoutManager = LinearLayoutManager(requireContext())
        binding.listFeed.setController(listFeedController)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.setModeFeed(getMode())

        viewModel.dataFeed.observe(viewLifecycleOwner, Observer {
            if (it.first.isNullOrEmpty()) {
                setError(resources.getString(R.string.empty_list))
            }
            else
            {
                setError(null)

                listFeedController.setData(it.first, it.second)
            }
        })

        viewModel.isRefreshed.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing = it
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            setError(it)
        })
    }

    fun setError(error: String?) {
        if (!error.isNullOrEmpty()) {
            binding.listFeed.visibility = View.GONE
            binding.textError.visibility = View.VISIBLE

            binding.textError.setText(error)
        }
        else
        {
            binding.textError.visibility = View.GONE
            binding.listFeed.visibility = View.VISIBLE
        }
    }

    fun getMode(): Int = arguments?.getInt(MODE_KEY) ?: LOCAL_MODE

    override fun savedClick(id: Long)
    {
        viewModel.savedPost(id)
    }

    override fun readMoreClick(id: Long)
    {
        router.navigateTo(PostScreen(id))
    }
}