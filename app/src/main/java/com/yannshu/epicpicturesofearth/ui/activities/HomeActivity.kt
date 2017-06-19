package com.yannshu.epicpicturesofearth.ui.activities

import android.os.Bundle
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.contracts.HomeActivityContract
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders
import com.yannshu.epicpicturesofearth.presenters.HomeActivityPresenter
import com.yannshu.epicpicturesofearth.ui.base.BaseActivity
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeActivityContract.View {

    @Inject
    lateinit var mPresenter: HomeActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun injectMembers(hasActivitySubComponentBuilders: HasActivitySubComponentBuilders) {
        (hasActivitySubComponentBuilders
                .getActivityComponentBuilder(HomeActivity::class.java) as HomeActivityComponent.Builder)
                .activityModule(HomeActivityComponent.HomeActivityModule(this))
                .build()
                .injectMembers(this)
    }
}
