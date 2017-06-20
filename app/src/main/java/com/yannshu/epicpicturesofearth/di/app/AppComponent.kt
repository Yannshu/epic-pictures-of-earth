package com.yannshu.epicpicturesofearth.di.app

import com.yannshu.epicpicturesofearth.EpicPicturesOfEarthApplication
import com.yannshu.epicpicturesofearth.di.activity.ActivityBindingModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ActivityBindingModule::class,
        NetModule::class,
        RepositoryModule::class
))
interface AppComponent {
    fun inject(application: EpicPicturesOfEarthApplication): EpicPicturesOfEarthApplication
}
