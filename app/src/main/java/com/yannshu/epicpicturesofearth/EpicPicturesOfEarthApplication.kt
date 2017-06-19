package com.yannshu.epicpicturesofearth

import android.app.Activity
import android.app.Application
import com.yannshu.epicpicturesofearth.di.activity.ActivityComponentBuilder
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders
import com.yannshu.epicpicturesofearth.di.app.AppComponent
import com.yannshu.epicpicturesofearth.di.app.AppModule
import com.yannshu.epicpicturesofearth.di.app.DaggerAppComponent
import javax.inject.Inject
import javax.inject.Provider

class EpicPicturesOfEarthApplication : Application(), HasActivitySubComponentBuilders {
    @Inject
    lateinit var mActivityComponentBuilders: Map<Class<out Activity>, @JvmSuppressWildcards Provider<ActivityComponentBuilder<*, *>>>

    lateinit var mAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        mAppComponent.inject(this)
    }

    override fun getActivityComponentBuilder(activityClass: Class<out Activity>): ActivityComponentBuilder<*, *>? {
        return mActivityComponentBuilders[activityClass]?.get();
    }
}