package com.yannshu.epicpicturesofearth.ui.activities

import com.yannshu.epicpicturesofearth.di.activity.ActivityComponent
import com.yannshu.epicpicturesofearth.di.activity.ActivityComponentBuilder
import com.yannshu.epicpicturesofearth.di.activity.ActivityModule
import com.yannshu.epicpicturesofearth.di.activity.ActivityScope
import com.yannshu.epicpicturesofearth.presenters.HomeActivityPresenter
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
        fun provideHomeActivityPresenter(): HomeActivityPresenter {
            return HomeActivityPresenter()
        }
    }
}
