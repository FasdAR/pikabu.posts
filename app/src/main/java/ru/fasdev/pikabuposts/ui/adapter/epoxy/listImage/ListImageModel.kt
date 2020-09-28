package ru.fasdev.pikabuposts.ui.adapter.epoxy.listImage

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.app.glide.getImageRequestOprion

@EpoxyModelClass(layout = R.layout.item_image)
abstract class ListImageModel : EpoxyModelWithHolder<ListImageModel.Holder>()
{
    class Holder: EpoxyHolder()
    {
        lateinit var mainImage: ImageView

        lateinit var context: Context

        override fun bindView(itemView: View)
        {
            mainImage = itemView.findViewById(R.id.main_image)
            context = itemView.context.applicationContext
        }
    }

    @EpoxyAttribute
    lateinit var url: String

    override fun bind(holder: Holder)
    {
        Glide
            .with(holder.context)
            .load(url)
            .apply(getImageRequestOprion())
            .transition(DrawableTransitionOptions.withCrossFade())
            .dontTransform()
            .into(holder.mainImage);
    }

    override fun unbind(holder: Holder) {
        super.unbind(holder)

        Glide
            .with(holder.context)
            .clear(holder.mainImage)
    }
}