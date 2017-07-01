package com.yannshu.epicpicturesofearth.di.activity

import com.yannshu.epicpicturesofearth.view.activities.HomeActivity
import com.yannshu.epicpicturesofearth.view.activities.HomeActivityComponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(HomeActivityComponent::class))
abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    abstract fun homeActivityComponentBuilder(impl: HomeActivityComponent.Builder): ActivityComponentBuilder<*, *>
}