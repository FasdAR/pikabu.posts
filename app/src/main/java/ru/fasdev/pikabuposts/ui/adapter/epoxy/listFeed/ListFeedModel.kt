package ru.fasdev.pikabuposts.ui.adapter.epoxy.listFeed

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.domain.post.model.Post

@EpoxyModelClass(layout = R.layout.item_post)
abstract class ListFeedModel : EpoxyModelWithHolder<ListFeedModel.Holder>(), View.OnClickListener
{
    class Holder: EpoxyHolder()
    {
        lateinit var rootView: View
        lateinit var title: TextView
        lateinit var mainImage: ImageView
        lateinit var body: TextView
        lateinit var readMoreBtn: TextView
        lateinit var saveBtn: AppCompatButton

        lateinit var resources: Resources
        lateinit var context: Context

        override fun bindView(itemView: View)
        {
            rootView = itemView
            title = itemView.findViewById(R.id.title)
            mainImage = itemView.findViewById(R.id.main_image)
            body = itemView.findViewById(R.id.body)
            readMoreBtn = itemView.findViewById(R.id.read_more)
            saveBtn = itemView.findViewById(R.id.save_btn)

            resources = itemView.resources
            context = itemView.context.applicationContext
        }
    }

    interface Listener
    {
        fun savedClick(id: Long)
        fun readMoreClick(id: Long)
    }

    @EpoxyAttribute
    lateinit var post: Post

    @EpoxyAttribute
    lateinit var listener: Listener

    override fun bind(holder: Holder)
    {
        holder.title.setText(post.title)
        holder.body.setText(post.body)

        if (post.body.isNullOrEmpty())
            holder.body.visibility = View.GONE
        else
            holder.body.visibility = View.VISIBLE

        holder.body.post {
            val layout = holder.body.layout
            if (layout != null && layout.lineCount > 0 && layout.getEllipsisCount(layout.lineCount - 1) > 0)
            {
                holder.readMoreBtn.visibility = View.VISIBLE
            }
            else
            {
                val imageSize = post.images?.size ?: 0

                if (imageSize > 1)
                    holder.readMoreBtn.visibility = View.VISIBLE
                else
                    holder.readMoreBtn.visibility = View.GONE
            }
        }

        if (post.isSaved)
        {
            holder.saveBtn.setText(holder.resources.getString(R.string.remove_save))
            holder.saveBtn.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_cloud_done,
                0,
                0,
                0
            )
        }
        else
        {
            holder.saveBtn.setText(holder.resources.getString(R.string.save))
            holder.saveBtn.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_cloud_download,
                0,
                0,
                0
            )
        }

        holder.saveBtn.setOnClickListener(this)
        holder.readMoreBtn.setOnClickListener(this)
        holder.rootView.setOnClickListener(this)

        if (!post.images.isNullOrEmpty())
        {
            holder.mainImage.visibility = View.VISIBLE

            Glide
                .with(holder.context)
                .load(post.images?.get(0))
                .placeholder(R.drawable.list_placeholder_img)
                .error(R.drawable.list_placeholder_err_img)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.mainImage);
        }
        else
        {
            holder.mainImage.visibility = View.GONE
        }
    }

    override fun unbind(holder: Holder) {
        super.unbind(holder)

        Glide
            .with(holder.context)
            .clear(holder.mainImage)
    }

    override fun onClick(view: View?)
    {
        when (view?.id) {
            R.id.save_btn -> {
                listener.savedClick(post.id)
            }
            R.id.read_more -> {
                listener.readMoreClick(post.id)
            }
            R.id.root_view -> {
                listener.readMoreClick(post.id)
            }
        }
    }
}