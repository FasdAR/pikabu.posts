package ru.fasdev.pikabuposts.app.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class ZipLiveData
{
    companion object {
        fun <A, B> zipLiveData(a: LiveData<A>, b: LiveData<B>): LiveData<Pair<A, B>> {
            return MediatorLiveData<Pair<A, B>>().apply {
                var liveDataA: A? = null
                var liveDataB: B? = null

                fun update() {
                    if (liveDataA != null && liveDataB != null)
                        this.value = Pair(liveDataA!!, liveDataB!!)
                }

                addSource(a) {
                    liveDataA = it
                    update()
                }

                addSource(b) {
                    liveDataB = it
                    update()
                }
            }
        }
    }
}