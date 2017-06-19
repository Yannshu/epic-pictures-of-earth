package com.yannshu.epicpicturesofearth.di.app

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(internal val application: Application) {

    internal val sharedPreferences: SharedPreferences
        @Provides
        @Singleton
        get() = application.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

    internal val context: Context
        @Provides
        get() = application.applicationContext

    internal val resources: Resources
        @Provides
        get() = application.resources

    companion object {
        private val SHARED_PREF_NAME = "AppPrefs"
    }
}
