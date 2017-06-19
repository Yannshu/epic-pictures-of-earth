package com.yannshu.epicpicturesofearth.di.activity

import android.app.Activity

interface HasActivitySubComponentBuilders {
    fun getActivityComponentBuilder(activityClass: Class<out Activity>): ActivityComponentBuilder<*, *>?
}