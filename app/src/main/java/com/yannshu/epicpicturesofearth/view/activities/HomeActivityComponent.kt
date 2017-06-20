package com.yannshu.epicpicturesofearth.view.activities

import com.yannshu.epicpicturesofearth.di.activity.ActivityComponent
import com.yannshu.epicpicturesofearth.di.activity.ActivityComponentBuilder
import com.yannshu.epicpicturesofearth.di.activity.ActivityModule
import com.yannshu.epicpicturesofearth.di.activity.ActivityScope
import com.yannshu.epicpicturesofearth.view.model.PicturesMetadataViewModel
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(HomeActivityComponent.HomeActivityModule::class))
interface HomeActivityComponent : ActivityComponent<HomeActivity> {

    @Subcomponent.Builder
    interface Builder : ActivityComponentBuilder<HomeActivityModule, HomeActivityComponent>

    @Module
    class HomeActivityModule(activity: HomeActivity) : ActivityModule<HomeActivity>(activity) {
        @ActivityScope
        @Provides
        fun providePicturesMetadataViewModel(): PicturesMetadataViewModel {
            return PicturesMetadataViewModel()
        }
    }
}
