package ru.fasdev.pikabuposts.ui.view.fragmentPost

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.post_fragment.*
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.app.glide.getImageRequestOprion
import ru.fasdev.pikabuposts.app.lifecycle.ViewModelFactory
import ru.fasdev.pikabuposts.app.lifecycle.injectViewModel
import ru.fasdev.pikabuposts.databinding.PostFragmentBinding
import ru.fasdev.pikabuposts.ui.adapter.epoxy.listImage.ListImageController
import ru.fasdev.pikabuposts.ui.view.activityMain.MainActivity
import ru.fasdev.pikabuposts.ui.view.fragmentMainFeed.MainFeedFragment
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class PostFragment : Fragment(), View.OnClickListener
{
    companion object
    {
        const val ID_KEY = "ik"

        fun newInstance(id: Long): PostFragment {
            val args = bundleOf(ID_KEY to id)
            return PostFragment().apply {
                arguments = args
            }
        }
    }

    @Inject
    lateinit var appCompactActivity: AppCompatActivity

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var router: Router

    private lateinit var viewModel: PostViewModel
    private lateinit var binding: PostFragmentBinding

    val fragmentPostComponent by lazy {
        return@lazy ((requireActivity() as MainActivity).activitySubComponent)
            .fragmentComponent()
            .build()
            .feedComponent()
            .build()
    }

    val listImageController = ListImageController()

    override fun onAttach(context: Context)
    {
        super.onAttach(context)

        fragmentPostComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = PostFragmentBinding.inflate(inflater)

        val toolbar = binding.root.findViewById<Toolbar>(R.id.toolbar)

        appCompactActivity.setSupportActionBar(toolbar)
        appCompactActivity.supportActionBar?.title = ""

        appCompactActivity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        appCompactActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setTitle(resources.getString(R.string.post))

        binding.listImages.layoutManager = LinearLayoutManager(requireContext())
        binding.listImages.setController(listImageController)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.setIdPost(getIdKey())

        viewModel.post.observe(viewLifecycleOwner, Observer {
            if (it != null)
            {
                binding.post.visibility = View.VISIBLE
                binding.title.setText(it.title)
                binding.body.setText(it.body)

                Glide
                    .with(this)
                    .load(it.images?.get(0))
                    .apply(getImageRequestOprion())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.root.findViewById<ImageView>(R.id.main_image));

                val imageSize = it.images?.size ?: 0

                if (imageSize > 1)
                {
                    binding.listImages.visibility = View.VISIBLE
                    listImageController.setData(it.images?.subList(1, imageSize))
                }
                else
                {
                    binding.listImages.visibility = View.GONE
                }

                if (it.isSaved)
                {
                    binding.saveBtn.setText(resources.getString(R.string.remove_save))
                    binding.saveBtn.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_cloud_done,
                        0,
                        0,
                        0
                    )
                }
                else
                {
                    binding.saveBtn.setText(resources.getString(R.string.save))
                    binding.saveBtn.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_cloud_download,
                        0,
                        0,
                        0
                    )
                }

                binding.saveBtn.setOnClickListener(this)
            }
            else
            {
                binding.post.visibility = View.GONE
            }
        })

        viewModel.isRefreshed.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing = it
            binding.swipeRefresh.isEnabled = it
        })
    }

    fun getIdKey(): Long = arguments?.getLong(ID_KEY) ?: 0

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            android.R.id.home -> {
                router.exit()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(view: View?)
    {
        when (view?.id)
        {
            R.id.save_btn -> {
                viewModel.savedPost()
            }
        }
    }
}