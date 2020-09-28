package ru.fasdev.pikabuposts.app.network

import android.content.Context
import ru.fasdev.pikabuposts.R
import ru.fasdev.pikabuposts.data.network.NetworkErrorInteractor
import java.net.UnknownHostException

class NetworkErrorInteractorImpl(val context: Context) : NetworkErrorInteractor
{
    override fun getError(ex: Throwable): String
    {
        when (ex)
        {
            is UnknownHostException -> {
                return context.resources.getString(R.string.no_internet)
            }
            else -> {
                return context.resources.getString(R.string.no_internet)
            }
        }
    }
}