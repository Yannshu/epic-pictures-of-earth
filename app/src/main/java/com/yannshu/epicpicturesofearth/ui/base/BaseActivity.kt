package com.yannshu.epicpicturesofearth.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yannshu.epicpicturesofearth.di.activity.HasActivitySubComponentBuilders

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        retrieveIntentBundle(intent.extras)
        setUpActivityComponents()
        super.onCreate(savedInstanceState)
    }

    protected fun retrieveIntentBundle(extras: Bundle?) {
    }

    private fun setUpActivityComponents() {
        injectMembers(applicationContext as HasActivitySubComponentBuilders)
    }

    protected abstract fun injectMembers(hasActivitySubComponentBuilders: HasActivitySubComponentBuilders)
}