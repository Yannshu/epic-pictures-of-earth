package com.yannshu.epicpicturesofearth.di.activity

import com.yannshu.epicpicturesofearth.view.activities.HomeActivity
import com.yannshu.epicpicturesofearth.view.activities.HomeActivityComponent
import com.yannshu.epicpicturesofearth.view.activities.PictureActivity
import com.yannshu.epicpicturesofearth.view.activities.PictureActivityComponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(HomeActivityComponent::class, PictureActivityComponent::class))
abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    abstract fun homeActivityComponentBuilder(impl: HomeActivityComponent.Builder): ActivityComponentBuilder<*, *>

    @Binds
    @IntoMap
    @ActivityKey(PictureActivity::class)
    abstract fun pictureActivityComponentBuilder(impl: PictureActivityComponent.Builder): ActivityComponentBuilder<*, *>

}