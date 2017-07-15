package com.yannshu.epicpicturesofearth.view.base

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.ButterKnife
import com.yannshu.epicpicturesofearth.R
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders

abstract class BaseActivity : AppCompatActivity(), LifecycleRegistryOwner {

    val mLifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        retrieveIntentBundle(intent.extras)
        setUpActivityComponents()
        super.onCreate(savedInstanceState)
    }

    protected open fun retrieveIntentBundle(extras: Bundle?) {
    }

    private fun setUpActivityComponents() {
        injectMembers(applicationContext as HasActivitySubComponentBuilders)
    }

    protected abstract fun injectMembers(hasActivitySubComponentBuilders: HasActivitySubComponentBuilders)

    override fun getLifecycle(): LifecycleRegistry {
        return mLifecycleRegistry
    }

    protected fun initActionBar(displayHomeAsUpEnabled: Boolean) {
        val toolBar = ButterKnife.findById<Toolbar>(this, R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
    }
}