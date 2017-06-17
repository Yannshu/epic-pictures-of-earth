package com.yannshu.epicpicturesofearth.ui.activities

import com.yannshu.epicpicturesofearth.di.activity.ActivityComponent
import com.yannshu.epicpicturesofearth.di.activity.ActivityComponentBuilder
import com.yannshu.epicpicturesofearth.di.activity.ActivityModule
import com.yannshu.epicpicturesofearth.di.activity.ActivityScope
import dagger.Module
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(MainActivityComponent.MainActivityModule::class))
interface MainActivityComponent : ActivityComponent<MainActivity> {

    @Subcomponent.Builder
    interface Builder : ActivityComponentBuilder<MainActivityModule, MainActivityComponent>

    @Module
    class MainActivityModule(activity: MainActivity) : ActivityModule<MainActivity>(activity) {
        /*
            @ActivityScope
            @Provides
            public MainPresenter provideMainPresenter() {
                return new MainPresenter();
            }
        */
    }
}
