package com.yannshu.epicpicturesofearth.di.app

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import com.yannshu.epicpicturesofearth.utils.PictureUrlBuilder
import dagger.Module
import dagger.Provides

@Module
class AppModule(internal val application: Application) {

    companion object {
        private val SHARED_PREF_NAME = "AppPrefs"
    }

    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)
    }

    @Provides
    fun provideAppContext(): Context {
        return application.applicationContext
    }

    @Provides
    fun provideResources(): Resources {
        return application.resources
    }

    @Provides
    fun providePictureUrlBuilder(): PictureUrlBuilder {
        return PictureUrlBuilder()
    }
}
