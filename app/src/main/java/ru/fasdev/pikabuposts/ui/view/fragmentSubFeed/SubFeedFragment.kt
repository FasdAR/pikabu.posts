package ru.fasdev.pikabuposts.ui.view.fragmentSubFeed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.databinding.SubFeedFragmentBinding

class SubFeedFragment : Fragment()
{

    companion object
    {
        const val NETWORK_MODE = 0;
        const val LOCAL_MODE = 0;

        fun newInstance(mode: Int) = SubFeedFragment()
    }

    private lateinit var viewModel: SubFeedViewModel
    private lateinit var binding: SubFeedFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = SubFeedFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SubFeedViewModel::class.java)
    }
}