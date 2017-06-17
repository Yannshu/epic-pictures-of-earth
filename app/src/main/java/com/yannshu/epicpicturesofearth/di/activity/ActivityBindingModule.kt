package com.yannshu.epicpicturesofearth.di.activity

import com.yannshu.epicpicturesofearth.ui.activities.MainActivity
import com.yannshu.epicpicturesofearth.ui.activities.MainActivityComponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(MainActivityComponent::class))
abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun mainActivityComponentBuilder(impl: MainActivityComponent.Builder): ActivityComponentBuilder<*, *>
}