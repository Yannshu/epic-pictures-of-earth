package com.yannshu.epicpicturesofearth.di.activity

import com.yannshu.epicpicturesofearth.ui.activities.BaseActivity

import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule<out T : BaseActivity>(protected val activity: T) {

    @Provides
    @ActivityScope
    fun provideActivity(): T {
        return activity
    }
}
