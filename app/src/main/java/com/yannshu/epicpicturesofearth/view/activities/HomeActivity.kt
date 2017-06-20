package com.yannshu.epicpicturesofearth.view.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders
import com.yannshu.epicpicturesofearth.view.base.BaseActivity
import com.yannshu.epicpicturesofearth.view.model.PicturesMetadataViewModel
import javax.inject.Inject


class HomeActivity : BaseActivity() {

    @Inject
    lateinit var mViewModel: PicturesMetadataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mViewModel.init("2017-06-19")
        mViewModel.mPicturesMetadata?.observe(this, Observer {

        });
    }

    override fun injectMembers(hasActivitySubComponentBuilders: HasActivitySubComponentBuilders) {
        (hasActivitySubComponentBuilders
                .getActivityComponentBuilder(HomeActivity::class.java) as HomeActivityComponent.Builder)
                .activityModule(HomeActivityComponent.HomeActivityModule(this))
                .build()
                .injectMembers(this)
    }
}
