package ru.fasdev.pikabuposts.app.di.component

import dagger.Component
import dagger.Subcomponent
import ru.fasdev.pikabuposts.app.di.module.postFragment.PostFragmentModule
import ru.fasdev.pikabuposts.app.di.scope.FragmentScope
import ru.fasdev.pikabuposts.app.di.scope.PostFragmentScope

@PostFragmentScope
@Subcomponent(modules = [PostFragmentModule::class])
interface PostFragmentComponent
{
    @Subcomponent.Builder
    interface Builder {
        fun build(): PostFragmentComponent
    }
}