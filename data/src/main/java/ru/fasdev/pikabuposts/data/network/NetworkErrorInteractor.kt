package ru.fasdev.pikabuposts.data.network

interface NetworkErrorInteractor
{
    fun getError(ex: Throwable): String
}