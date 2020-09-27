package ru.fasdev.pikabuposts.app.glide

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import ru.fasdev.pikabuposts.R

fun getImageRequestOprion(): RequestOptions
{
    return RequestOptions()
        .placeholder(R.drawable.list_placeholder_img)
        .error(R.drawable.list_placeholder_err_img)
}