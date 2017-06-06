package com.yannshu.epicpicturesofearth.di.fragment

import dagger.Module
import dagger.Provides

@Module
abstract class FragmentModule<T>(protected val fragment: T) {

    @Provides
    @FragmentScope
    fun provideFragment(): T {
        return fragment
    }
}
