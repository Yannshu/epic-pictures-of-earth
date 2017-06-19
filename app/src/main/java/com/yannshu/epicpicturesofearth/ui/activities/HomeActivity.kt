package com.yannshu.epicpicturesofearth.ui.activities

import android.os.Bundle
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders
import com.yannshu.epicpicturesofearth.ui.base.BaseActivity


class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun injectMembers(hasActivitySubComponentBuilders: HasActivitySubComponentBuilders) {
        (hasActivitySubComponentBuilders
                .getActivityComponentBuilder(HomeActivity::class.java) as HomeActivityComponent.Builder)
                .activityModule(HomeActivityComponent.HomeActivityModule(this))
                .build()
                .injectMembers(this)
    }
}
