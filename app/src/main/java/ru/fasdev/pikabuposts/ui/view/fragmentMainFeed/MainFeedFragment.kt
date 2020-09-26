package ru.fasdev.pikabuposts.ui.view.fragmentMainFeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.fasdev.pikabuposts.databinding.FeedFragmentBinding

class MainFeedFragment : Fragment()
{
    companion object
    {
        fun newInstance() = MainFeedFragment()
    }

    private lateinit var viewModel: MainFeedViewModel
    private lateinit var binding: FeedFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FeedFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainFeedViewModel::class.java)
    }
}