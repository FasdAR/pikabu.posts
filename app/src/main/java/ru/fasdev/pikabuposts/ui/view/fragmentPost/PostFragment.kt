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
        return@lazy (requireActivity() as MainActivity).feedComponent
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
        setHasOptionsMenu(true)

        toolbar.setTitle(resources.getString(R.string.post))

        binding.listImages.layoutManager = LinearLayoutManager(requireContext())
        binding.listImages.setController(listImageController)

        binding.repeatBtn.setOnClickListener(this)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        viewModel.id = getIdKey()

        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty())
            {
                binding.post.visibility = View.VISIBLE
                binding.errLayout.visibility = View.GONE
            }
            else
            {
                binding.post.visibility = View.GONE
                binding.errLayout.visibility = View.VISIBLE

                binding.error.setText(it)
            }
        })

        viewModel.data.observe(viewLifecycleOwner, Observer {
            val post = it.first
            val isSaved = it.second

            if (isSaved)
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

            if (post != null)
            {
                binding.post.visibility = View.VISIBLE
                binding.title.setText(post.title)
                binding.body.setText(post.body)

                if (post.body.isNullOrEmpty())
                    binding.body.visibility = View.GONE
                else
                    binding.body.visibility = View.VISIBLE

                val imageSize = post.images?.size ?: 0
                val mainImage = binding.root.findViewById<ImageView>(R.id.main_image)

                if (imageSize != 0)
                {
                    mainImage.visibility = View.VISIBLE

                    Glide
                        .with(this)
                        .load(post.images?.get(0))
                        .apply(getImageRequestOprion())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .dontTransform()
                        .into(mainImage);

                    if (imageSize >= 1)
                    {
                        binding.listImages.visibility = View.VISIBLE
                        listImageController.setData(post.images?.subList(1, imageSize))
                    }
                    else
                    {
                        binding.listImages.visibility = View.GONE
                    }
                }
                else
                {
                    binding.listImages.visibility = View.GONE
                    mainImage.visibility = View.GONE
                }

                binding.saveBtn.setOnClickListener(this)
            }
            else
            {
                binding.post.visibility = View.GONE
            }
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
            R.id.repeat_btn -> {
                viewModel.id = getIdKey()
            }
        }
    }
}